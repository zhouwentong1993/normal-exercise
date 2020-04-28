package com.wentong.demo;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.security.ProtectionDomain;

import static org.objectweb.asm.Opcodes.ASM7;

public class AsmTest1 {

    public static class MyMethodVisitor extends AdviceAdapter {

        protected MyMethodVisitor(MethodVisitor methodVisitor, int i1, String s, String s1) {
            super(ASM7, methodVisitor, i1, s, s1);
        }

        @Override
        protected void onMethodEnter() {
            mv.visitInsn(ICONST_1);
            mv.visitInsn(IRETURN);
        }
    }

    public static class MyClassVisitor extends ClassVisitor {

        public MyClassVisitor(ClassVisitor classVisitor) {
            super(ASM7, classVisitor);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
            if (name.equals("canLoad")) {
                return new MyMethodVisitor(mv, access, name, desc);
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
                ClassVisitor myClassVisitor = new MyClassVisitor(classWriter);
                classReader.accept(myClassVisitor, ClassReader.SKIP_FRAMES | ClassReader.SKIP_DEBUG);
                return classWriter.toByteArray();
            } else {
                return classfileBuffer;
            }
        }
    }
    public static void premain(String agentArgs, Instrumentation inst) throws ClassNotFoundException, UnmodifiableClassException {
        inst.addTransformer(new MyClassFileTransformer(), true);
    }
}
