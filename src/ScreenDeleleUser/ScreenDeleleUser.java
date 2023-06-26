/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ScreenDeleleUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

    /*
     * Esse código define a tela de exclusão de usuário com um campo de texto para inserir o ID a ser excluído
     * e um botão para realizao a exclusão
     */
public class ScreenDeleleUser extends JFrame {

    private static final String DB_URL = "jdbc:sqlite:db/users.db";
    private JTextField userIdField;

    public ScreenDeleleUser() {
        setTitle("Excluir Usuário");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel userIdLabel = new JLabel("ID do Usuário:");
        userIdField = new JTextField(10);

        JButton deleteButton = new JButton("Excluir");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int userId = Integer.parseInt(userIdField.getText());
                deleteUser(userId);
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

    /*
     * Esse código define a posição e a adição do campo para inserir o ID do usuário, o botão de excluir e voltar
     * 
     */
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(userIdLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(userIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(deleteButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(backButton, gbc);

        add(panel);
    }


    /*
     * Esse método é responsável por executar a exclusão do usuário do banco de dados com base no ID inserido
     * 
     */
    private void deleteUser(int userId) {
        try (Connection conn = DriverManager.getConnection(DB_URL); PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE id = ?")) {

            stmt.setInt(1, userId);

            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "Usuário excluído com sucesso.");
            } else {
                JOptionPane.showMessageDialog(this, "Não foi possível encontrar o usuário com o ID fornecido.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao excluir o usuário.");
        }
    }


    /*
     * Esse método torna visível ao usuário a tela de exclusão de usuário
     * 
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ScreenDeleleUser deleteUser = new ScreenDeleleUser();
                deleteUser.setVisible(true);
            }
        });
    }
}
