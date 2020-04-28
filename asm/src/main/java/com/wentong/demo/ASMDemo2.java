package com.wentong.demo;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import org.objectweb.asm.*;

import java.io.FileInputStream;

import static org.objectweb.asm.Opcodes.*;

/*
    动态地给类加字段和方法
 */
public class ASMDemo2 {

    public static void main(String[] args) throws Exception {
        byte[] bytes = IoUtil.readBytes(new FileInputStream("/Users/finup123/IdeaProjects/exercise/asm/src/main/java/com/wentong/demo/Target.class"));
        ClassReader classReader = new ClassReader(bytes);
        ClassWriter classWriter = new ClassWriter(0);
        ClassVisitor classVisitor = new ClassVisitor(ASM5, classWriter) {

            @Override
            public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
                // 这个方法是有返回值的，如果返回 null，代表移除该字段。
                if (name.equals("a")) {
                    return null;
                } else {
                    return super.visitField(access, name, descriptor, signature, value);
                }
            }

            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                // 移除 get 方法，在调用 visitEnd 方法时再添加一个 get，并修改原始行为
                if (name.equals("get")) {
                    return null;
                } else {
                    return super.visitMethod(access, name, descriptor, signature, exceptions);
                }
            }

            @Override
            public void visitEnd() {
                super.visitEnd();
                // ① 要用 this
                // ② 这里的变量名为 new，虽然在写 Java 时不能用，但是通过 ASM 就可以用
                FieldVisitor fieldVisitor = this.visitField(Opcodes.ACC_PUBLIC, "new", "Ljava/lang/String", null, "test");
                if (fieldVisitor != null) {
                    fieldVisitor.visitEnd();
                }
                MethodVisitor method = this.visitMethod(Opcodes.ACC_PUBLIC, "metho1d", "(ILjava/lang/String;)V", null, null);
                if (method != null) {
                    method.visitEnd();
                }
                MethodVisitor getMethodVisitor = this.visitMethod(ACC_PUBLIC, "get1", "(I)I", null, null);
                if (getMethodVisitor != null) {
                    getMethodVisitor.visitCode();
                    getMethodVisitor.visitVarInsn(ILOAD, 1);
                    getMethodVisitor.visitVarInsn(BIPUSH,100);
                    getMethodVisitor.visitInsn(IADD);
                    getMethodVisitor.visitInsn(IRETURN);
                    // 触发计算
                    getMethodVisitor.visitMaxs(0, 0);
                    getMethodVisitor.visitEnd();
                }
            }
        };
        classReader.accept(classVisitor, 0);
        byte[] bytes1 = classWriter.toByteArray();
        FileUtil.writeBytes(bytes1, "/Users/finup123/IdeaProjects/exercise/asm/src/main/java/com/wentong/demo/Target1.class");
    }

}
