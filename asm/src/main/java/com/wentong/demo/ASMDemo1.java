package com.wentong.demo;

import cn.hutool.core.io.IoUtil;
import org.objectweb.asm.*;

import java.io.FileInputStream;

import static org.objectweb.asm.Opcodes.ASM5;

/*
    通过 .class 文件的字节数组来获取类的具体细节
 */
public class ASMDemo1 {
    public static void main(String[] args) throws Exception {
        byte[] bytes = IoUtil.readBytes(new FileInputStream("/Users/finup123/IdeaProjects/exercise/asm/src/main/java/com/wentong/demo/Target.class"));
        ClassReader classReader = new ClassReader(bytes);
        ClassWriter classWriter = new ClassWriter(0);
        ClassVisitor classVisitor = new ClassVisitor(ASM5, classWriter) {
            @Override
            public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
                System.out.println("ASMDemo1.visitField:name: " + name);
                return super.visitField(access, name, descriptor, signature, value);
            }

            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                System.out.println("ASMDemo1.visitMethod name: " + name);
                return super.visitMethod(access, name, descriptor, signature, exceptions);
            }
        };
        classReader.accept(classVisitor, ClassReader.SKIP_CODE | ClassReader.SKIP_DEBUG);
    }
}