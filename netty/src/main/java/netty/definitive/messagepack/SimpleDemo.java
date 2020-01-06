package netty.definitive.messagepack;

import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

import java.util.ArrayList;
import java.util.List;

public class SimpleDemo {
    public static void main(String[] args) throws Exception {
        List<String> list = new ArrayList<>();
        list.add("test1");
        list.add("test2");
        list.add("test3");
        MessagePack messagePack = new MessagePack();
        byte[] raw = messagePack.write(list);
        List<String> read = messagePack.read(raw, Templates.tList(Templates.TString));
        System.out.println(read.get(0));
        System.out.println(read.get(1));
        System.out.println(read.get(2));

    }
}
