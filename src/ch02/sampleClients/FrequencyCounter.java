package ch02.sampleClients;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

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

    public boolean contains(Key key) {
        return this.key != null && this.key.equals(key);
    }
}

public class FrequencyCounter {
    public static void main(String[] args) throws FileNotFoundException {
        int minlen = 999999;

        ST<String, Integer> st = new ST<>();
        File file;
        final JFileChooser fc = new JFileChooser();     // 파일 선택기를 사용
        if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) file = fc.getSelectedFile();
        else {
            JOptionPane.showMessageDialog(null, "파일을 선택하세요", "오류", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Scanner sc = new Scanner(file);
            long start = System.currentTimeMillis();
            while (sc.hasNext()) {
                String word = sc.next();
                if (word.length() < minlen) continue;
                if (!st.contains(word)) st.put(word, 1);
                else st.put(word, st.get(word) + 1);
            }
            String maxKey = ""; int maxValue = 0;
            for (String word : st.keys()) {
                if (st.get(word) > maxValue) { maxKey = word; maxValue = st.get(word); }
            }
            long end = System.currentTimeMillis();
            System.out.println(maxKey + " " + maxValue);
            System.out.println("소요 시간 = " + (end - start) + "ms");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
