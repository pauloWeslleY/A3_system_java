/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ScreenCreateUser;

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
 * 
 */
public class ScreenCreateUser extends JFrame {

    private static final String DB_URL = "jdbc:sqlite:db/users.db";
    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;


    /*
     * Esse código cria uma janela de criação de usuário com campos para inserir informações do 
     * usuário ("Nome do Usuário, Email, Senha") e um botão para realizar a criação com base nessas informações
     * 
     */
    public ScreenCreateUser() {
        setTitle("Criar Usuário");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel usernameLabel = new JLabel("Nome do Usuário:");
        usernameField = new JTextField(20);

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Senha:");
        passwordField = new JPasswordField(20);

        JButton createButton = new JButton("Criar usuário");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                createUser(username, email, password);
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
     * o código está definindo a disposição e a organização dos componentes visuais dentro do painel,
     * em seguida, adicionando o painel à tela.
     * 
     */
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(createButton, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(backButton, gbc);

        add(panel);
    }

    /*
     * Esse método tem a fumção de criar um novo usuário no banco de dados assim como estabelecer a
     * conexão com o banco
     * 
     */
    private void createUser(String username, String email, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL); PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (username, email, password) VALUES (?, ?, ?)")) {

            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password);

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Usuário criado com sucesso.");
            } else {
                JOptionPane.showMessageDialog(this, "Falha ao criar usuário.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao criar o usuário.");
        }
    }


    /*
     * Esse método torna visível a tela de criação de usuário, exibindo-a na tela
     * 
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ScreenCreateUser createUser = new ScreenCreateUser();
                createUser.setVisible(true);
            }
        });
    }
}

