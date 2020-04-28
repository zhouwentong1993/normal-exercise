package com.wentong.demo;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import org.objectweb.asm.*;
import org.objectweb.asm.commons.AdviceAdapter;

import java.io.FileInputStream;

import static org.objectweb.asm.Opcodes.*;

/*
    MethodAdvice 应用
 */
public class ASMDemo3 {

    public static void main(String[] args) throws Exception {
        byte[] bytes = IoUtil.readBytes(new FileInputStream("/Users/finup123/IdeaProjects/exercise/asm/src/main/java/Target.class"));
        ClassReader classReader = new ClassReader(bytes);
        ClassWriter classWriter = new ClassWriter(0);
        ClassVisitor classVisitor = new ClassVisitor(ASM5, classWriter) {

            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);
                // 只处理 printf 方法
                if (!name.equals("printf")) {
                    return methodVisitor;
                } else {
                    Label startLabel = new Label();

                    return new AdviceAdapter(ASM7, methodVisitor, access, name, descriptor) {

                        @Override
                        protected void onMethodEnter() {
                            // 新增打印 System.out.println("enter" + name)
                            super.onMethodEnter();
                            methodVisitor.visitLabel(startLabel);
                            methodVisitor.visitFieldInsn(GETSTATIC,"java/lang/System","out","Ljava/io/PrintStream;");
                            methodVisitor.visitLdcInsn("enter" + name);
                            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
                        }



                        @Override
                        protected void onMethodExit(int opcode) {
                            super.onMethodExit(opcode);
                            methodVisitor.visitFieldInsn(GETSTATIC,"java/lang/System","out", "Ljava/io/PrintStream;");
                            // 代表抛出了异常，需要用 System.err 打印
                            if (opcode == Opcodes.ATHROW) {
                                methodVisitor.visitLdcInsn("err exit " + name);
                            } else {
                                methodVisitor.visitLdcInsn("normal exit " + name);
                            }
                            methodVisitor.visitMethodInsn(INVOKEVIRTUAL,"java/io/PrintStream","println","(Ljava/lang/String;)V",false);
                        }
                    };
                }
            }

            @Override
            public void visitEnd() {
                super.visitEnd();
                // ① 要用 this
                // ② 这里的变量名为 new，虽然在写 Java 时不能用，但是通过 ASM 就可以用
                // update 执行时会报错！
//                FieldVisitor fieldVisitor = this.visitField(Opcodes.ACC_PUBLIC, "new", "Ljava/lang/String", null, "test");
//                if (fieldVisitor != null) {
//                    fieldVisitor.visitEnd();
//                }
//                MethodVisitor method = this.visitMethod(Opcodes.ACC_PUBLIC, "metho1d", "(ILjava/lang/String;)V", null, null);
//                if (method != null) {
//                    method.visitEnd();
//                }
//                MethodVisitor getMethodVisitor = this.visitMethod(ACC_PUBLIC, "get1", "(I)I", null, null);
//                if (getMethodVisitor != null) {
//                    getMethodVisitor.visitCode();
//                    getMethodVisitor.visitVarInsn(ILOAD, 1);
//                    getMethodVisitor.visitVarInsn(BIPUSH,100);
//                    getMethodVisitor.visitInsn(IADD);
//                    getMethodVisitor.visitInsn(IRETURN);
//                    // 触发计算
//                    getMethodVisitor.visitMaxs(0, 0);
//                    getMethodVisitor.visitEnd();
//                }
            }
        };
        classReader.accept(classVisitor, 0);
        byte[] bytes1 = classWriter.toByteArray();
        FileUtil.writeBytes(bytes1, "/Users/finup123/IdeaProjects/exercise/asm/src/main/java/Target.class");
    }

}
