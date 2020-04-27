package com.wentong.demo;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import org.objectweb.asm.*;

import java.io.FileInputStream;

import static org.objectweb.asm.Opcodes.ASM5;

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
            }
        };
        classReader.accept(classVisitor, ClassReader.SKIP_CODE | ClassReader.SKIP_DEBUG);
        byte[] bytes1 = classWriter.toByteArray();
        FileUtil.writeBytes(bytes1, "/Users/finup123/IdeaProjects/exercise/asm/src/main/java/com/wentong/demo/Target1.class");
    }

}
