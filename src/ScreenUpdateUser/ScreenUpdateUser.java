/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ScreenUpdateUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author wesll
 */
public class ScreenUpdateUser extends JFrame {

    private static final String DB_URL = "jdbc:sqlite:db/users.db";
    private JTextField userIdField;
    private JTextField nameField;
    private JTextField emailField;

    public ScreenUpdateUser() {
        setTitle("Atualizar Usuário");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel userIdLabel = new JLabel("ID do Usuário:");
        userIdField = new JTextField(10);

        JLabel nameLabel = new JLabel("Novo Nome:");
        nameField = new JTextField(20);

        JLabel emailLabel = new JLabel("Novo Email:");
        emailField = new JTextField(20);

        JButton updateButton = new JButton("Atualizar");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int userId = Integer.parseInt(userIdField.getText());
                String newName = nameField.getText();
                String newEmail = emailField.getText();
                updateUser(userId, newName, newEmail);
            }
        });

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

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(userIdLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(userIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(updateButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(backButton, gbc);

        add(panel);
    }

    private void updateUser(int userId, String newName, String newEmail) {
        try (Connection conn = DriverManager.getConnection(DB_URL); PreparedStatement stmt = conn.prepareStatement("UPDATE users SET name = ?, email = ? WHERE id = ?")) {

            stmt.setString(1, newName);
            stmt.setString(2, newEmail);
            stmt.setInt(3, userId);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Usuário atualizado com sucesso.");
            } else {
                JOptionPane.showMessageDialog(this, "Não foi possível encontrar o usuário com o ID fornecido.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao atualizar o usuário.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ScreenUpdateUser updateUser = new ScreenUpdateUser();
                updateUser.setVisible(true);
            }
        });
    }
}
