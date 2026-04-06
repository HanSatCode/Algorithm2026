package ch02.sampleClients;

import java.nio.file.FileAlreadyExistsException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class ST<Key, Value> {
    Key key = null;
    Value value = null;

    public void put(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    public Value get(Key key) {
        if (this.key.equals(key)) return value;
        else return null;
    }

    public Iterable<Key> keys() {
        return new Iterable<Key>() {
            @Override
            public java.util.Iterator<Key> iterator() {
                return new java.util.Iterator<Key>() {
                    private boolean hasNext = (key != null);

                    @Override
                    public boolean hasNext() { return hasNext; }

                    @Override
                    public Key next() {
                        if (!hasNext) throw new java.util.NoSuchElementException();
                        hasNext = false;
                        return key;
                    }
                };
            }
        };
    }
}

public class TestClient {
    public static void main(String[] args) throws Exception {
        ST<String, Integer> st = new ST<>();
        File file;
        final JFileChooser fc = new JFileChooser();     // 파일 선택기를 사용
        if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) file = fc.getSelectedFile();
        else {
            JOptionPane.showMessageDialog(null, "파일을 선택하세요", "오류", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Scanner sc = null;
        try {
            sc = new Scanner(file);
            for(int i = 0; sc.hasNext(); i++) { String key = sc.next(); st.put(key, i); }
            for(String s : st.keys()) System.out.println(s + " " + st.get(s));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (sc != null) sc.close();
    }
}
