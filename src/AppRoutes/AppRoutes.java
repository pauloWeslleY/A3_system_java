package AppRoutes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ScreenCreateUser.ScreenCreateUser;
import ScreenDeleteUser.ScreenDeleteUser;
import ScreenUserList.ScreenUserList;
import ScreenUpdateUser.ScreenUpdateUser;


/**
 * @author Weslley
 * Neste classe se encontra as rotas do sistema
 */
public class AppRoutes extends JFrame {

   /**
    * Neste método AppRoutes tem os botões que leva pra sua respectiva tela
    */
    public AppRoutes() {

        /**
         * Configuração da interface do programa
         * Define o título da janela do aplicativo
         * Define a operação padrão de fechamento da janela
         * Define o tamanho da janela
         * Centraliza a janela na tela
         */
        setTitle("Menu do Sistema");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        /**
         * Criação do painel principal para a janela do programa
         * Cria um JPanel com o layout GridBagLayout
         * Cria GridBagConstraints para posicionar os componentes
         * Define as margens internas (insets) para os componentes
         */
        JPanel screen = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        /**
         * Criação do botão para criar um usuário
         * Cria um JButton com o texto "Criar Usuário"
         * Define o tamanho preferido do botão
         */
        JButton createUserButton = new JButton("Criar Usuário");
        createUserButton.setPreferredSize(new Dimension(200, 50));
        createUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScreenCreateUser createUser = new ScreenCreateUser();
                createUser.setVisible(true);
            }
        });


        /**
         * Criação do botão para listar usuários
         */
        JButton listUsersButton = new JButton("Listar Usuários");
        listUsersButton.setPreferredSize(new Dimension(200, 50));
        listUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScreenUserList userList = new ScreenUserList();
                userList.setVisible(true);
                userList.getUserList();
            }
        });

        /**
         * Criação do botão para atualizar um usuário
         */
        JButton updateUserButton = new JButton("Atualizar Usuário");
        updateUserButton.setPreferredSize(new Dimension(200, 50));
        updateUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScreenUpdateUser updateUser = new ScreenUpdateUser();
                updateUser.setVisible(true);
            }
        });

        /**
         * Criação do botão para excluir um usuário
         */
        JButton deleteUserButton = new JButton("Excluir Usuário");
        deleteUserButton.setPreferredSize(new Dimension(200, 50));
        deleteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScreenDeleteUser deleteUser = new ScreenDeleteUser();
                deleteUser.setVisible(true);
            }
        });


        // Adiciona os botões ao painel 'screen' usando GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        screen.add(createUserButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        screen.add(listUsersButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        screen.add(updateUserButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        screen.add(deleteUserButton, gbc);

        add(screen);
    }

    /**
    *
    *
    */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AppRoutes routes = new AppRoutes();
                routes.setVisible(true);
            }
        });
    }
}
