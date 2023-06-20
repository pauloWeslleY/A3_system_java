/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ScreenUserList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author wesll
 */
public class ScreenUserList extends JFrame {

    private static final String DB_URL = "jdbc:sqlite:db/users.db";
    private JTextArea userListArea;

    public ScreenUserList() {
        setTitle("Listar Usuários");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        userListArea = new JTextArea(10, 20);
        userListArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(userListArea);

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Voltar à tela anterior
                dispose(); // Fechar a tela atual
                // Código para exibir a tela anterior
                // Exemplo: new MenuFrame().setVisible(true);
            }
        });

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);
        add(panel);
        
        loadUserList();
    }

    public void loadUserList() {
        try (Connection conn = DriverManager.getConnection(DB_URL); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM users")) {

            while (rs.next()) {
                int userId = rs.getInt("id");
                String username = rs.getString("username");
                String email = rs.getString("email");

                String userString = "ID: " + userId + "\nNome: " + username + "\nEmail: " + email + "\n\n";
                userListArea.append(userString);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao listar os usuários.");
        }
    }

    public void getUserList() {
        StringBuilder userList = new StringBuilder();

        try (Connection conn = DriverManager.getConnection(DB_URL); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM users")) {

            userListArea.setText("");
            while (rs.next()) {
                int userId = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");

                userList.append("ID: ").append(userId).append("\n");
                userList.append("Username: ").append(username).append("\n");
                userList.append("Password: ").append(password).append("\n");
                userList.append("Email: ").append(email).append("\n");
                userList.append("---------------------\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            userList.append("Erro ao carregar a lista de usuários.");
        }

        userListArea.setText(userList.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ScreenUserList userList = new ScreenUserList();
                userList.setVisible(true);
            }
        });
    }
}
