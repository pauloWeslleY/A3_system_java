package AppRoutes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ScreenCreateUser.ScreenCreateUser;
import ScreenDeleleUser.ScreenDeleleUser;
import ScreenUserList.ScreenUserList;
import ScreenUpdateUser.ScreenUpdateUser;

       /*
        * Neste arquivo se encontra as rotas do sistema
        */
public class AppRoutes extends JFrame {

       /*
        * Neste método AppRoutes tem os botões que leva pra sua respectiva tela:
        * EX: Tela de criação de usuário
        */
    public AppRoutes() {

        /*
         * Esse método se refere a interface do programa
         *
         */
        setTitle("Menu do Sistema");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        /*
         * Esse código define o layout da interface principal
         *
         */
        JPanel screen = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        /*
         * Esse botão irá mostrar a interface para cadastro de usuários
         *
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


        /*
         * Esse botão irá mostrar a interface que lista todos os usuários cadastrados
         *
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

        /*
         * Esse botão irá mostrar a interface de atualização de usuário
         *
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

        /*
         * Esse botão irá mostrar a interface de exclusão de usuário
         *
         */
        JButton deleteUserButton = new JButton("Excluir Usuário");
        deleteUserButton.setPreferredSize(new Dimension(200, 50));
        deleteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScreenDeleleUser deleteUser = new ScreenDeleleUser();
                deleteUser.setVisible(true);
            }
        });

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

       /*
        * Esse método é o ponto de entrada para a execução do pragrama, garantindo uma inicialização
        * e exibição correta da interface do usuário. 
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
