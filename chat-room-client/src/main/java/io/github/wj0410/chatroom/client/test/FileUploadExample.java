package io.github.wj0410.chatroom.client.test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileUploadExample {

    public static void main(String[] args) {
        JFrame frame = new JFrame("File Upload Example");
        JButton uploadButton = new JButton("Upload File");

        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    // 用户选择了文件
                    java.io.File selectedFile = fileChooser.getSelectedFile();
                    // 执行上传文件的逻辑，例如将文件发送到服务器等
                    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                }
            }
        });

        frame.add(uploadButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}