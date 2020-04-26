package com.wentong.demo.bytecode;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.commons.AdviceAdapter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class AsmTest1 {

    public static class MyMethodVisitor extends AdviceAdapter {

        protected MyMethodVisitor(int i, MethodVisitor methodVisitor, int i1, String s, String s1) {
            super(i, methodVisitor, i1, s, s1);
        }

        @Override
        protected void onMethodEnter() {
            System.out.println("MyMethodVisitor.onMethodEnter");
            mv.visitInsn(ICONST_1);
            mv.visitInsn(IRETURN);
        }
    }

    public static class MyClassVisitor extends ClassVisitor {

        public MyClassVisitor(int i, ClassVisitor classVisitor) {
            super(i, classVisitor);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
            if (name.equals("canLoad")) {
                return new MyMethodVisitor(123, mv, access, name, desc);
            } else {
                return mv;
            }
        }
    }

    public static class MyClassFileTransformer implements ClassFileTransformer {

        @Override
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
            if (className.equals("me/ya/swing/StartupChecks")) {
                ClassReader classReader = new ClassReader(classfileBuffer);
                ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_FRAMES);
                ClassVisitor myClassVisitor = new MyClassVisitor(123, classWriter);
                classReader.accept(myClassVisitor, ClassReader.SKIP_FRAMES | ClassReader.SKIP_DEBUG);
                return classWriter.toByteArray();
            } else {
                return classfileBuffer;
            }
        }
    }
}
