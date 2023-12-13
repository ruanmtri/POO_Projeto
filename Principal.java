package trabalho_v2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Principal {
          
    public static int gerado = 0;
    
    private static void createAndShowGUI() {
                 
        JFrame frame = new JFrame("Sistema de Passagens");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        frame.setSize(screenSize.width, screenSize.height);
        frame.setLocationRelativeTo(null);

        frame.setLayout(new GridLayout(4, 1));
        
        CadastroPassagem cadastro = new CadastroPassagem();
        ControlCadastro control = new ControlCadastro(cadastro);
        ModelCadastro model = new ModelCadastro();
        ModelCadastroAdapter modelAdapter = new ModelCadastroAdapter(model);
        
        JButton button1 = new JButton("Cadastrar Companhia de Ônibus");
        JButton button2 = new JButton("Cadastrar Passageiro");
        JButton button3 = new JButton("Cadastrar Ônibus");
        JButton button4 = new JButton("Cadastrar Rota");
        JButton button5 = new JButton("Listar Passageiros");
        JButton button6 = new JButton("Listar Ônibus");
        JButton button7 = new JButton("Listar Companhias de Ônibus");
        JButton button8 = new JButton("Listar Rotas");
        JButton button9 = new JButton("Emitir Passagem");
        JButton button10 = new JButton("Listar Passagens Emitidas");
        JButton button11 = new JButton("Remover Ônibus");
        JButton button12 = new JButton("Remover Passageiro");
        JButton button13 = new JButton("Remover Rota");
        JButton button14 = new JButton("Remover Companhia");
        JButton button15 = new JButton("Carregar Ex do Arquivo");
        JButton button16 = new JButton("Carregar Ex do Codigo");
        
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.add(button5);
        frame.add(button6);
        frame.add(button7);
        frame.add(button8);
        frame.add(button9);
        frame.add(button10);
        frame.add(button11);
        frame.add(button12);
        frame.add(button13);
        frame.add(button14);
        frame.add(button15);
        frame.add(button16);
                        
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JDialog novaJanela = new JDialog(frame, "Cadastro de Companhia", true);
                novaJanela.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                novaJanela.setLayout(new BorderLayout());

                JPanel panel = new JPanel();
                novaJanela.add(panel);

                JTextField nomeCompanhiaField = new JTextField(20);
                panel.add(new JLabel("Nome da Companhia:"));
                panel.add(nomeCompanhiaField);

                JTextField tarifaField = new JTextField(10);
                panel.add(new JLabel("Tarifa:"));
                panel.add(tarifaField);

                JButton salvarButton = new JButton("Salvar");
                panel.add(salvarButton);

                salvarButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String nomeCompanhia = nomeCompanhiaField.getText();
                        String tarifaText = tarifaField.getText();

                        if (!nomeCompanhia.isEmpty() && !tarifaText.isEmpty()) {
                            try {
                                double tarifa = Double.parseDouble(tarifaText);
                                if (tarifa >= 1.3 && tarifa <= 2.0) {
                                    control.cadastrarCompanhia(nomeCompanhia);
                                    JOptionPane.showMessageDialog(null, "Companhia de Ônibus cadastrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                                    novaJanela.dispose();
                                } else {
                                    JOptionPane.showMessageDialog(null, "A tarifa deve estar entre 1.3 e 2.0.", "ERRO", JOptionPane.INFORMATION_MESSAGE);
                                }
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "A tarifa deve ser um valor numérico.", "ERRO", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Nome da Companhia e Tarifa não podem estar em branco.", "ERRO", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                });

                novaJanela.pack();
                novaJanela.setLocationRelativeTo(frame);
                novaJanela.setVisible(true);
            }
        });
        
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                                
                List<Onibus> onibus = control.listarOnibus();                                
                List<Companhia> companhias = control.listarCompanhias();
                List<Cidade> rotasOnibus = model.listarRotasValidas();
                JComboBox<Companhia> companhiaComboBox = new JComboBox<>(companhias.toArray(new Companhia[0]));
                JComboBox<Cidade> cidadeOrigemComboBox = new JComboBox<>(rotasOnibus.toArray(new Cidade[0]));
                JComboBox<Onibus> onibusComboBox = new JComboBox<>(onibus.toArray(new Onibus[0]));
                
                if (companhias != null && !companhias.isEmpty() && rotasOnibus != null && !rotasOnibus.isEmpty() && onibus != null && !onibus.isEmpty()) {                                  
                JDialog novaJanela = new JDialog(frame, "Cadastro de Passageiro", true);
                novaJanela.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                novaJanela.setLayout(new BorderLayout());

                JPanel panel = new JPanel();
                novaJanela.add(panel);

                JTextField nomePassageiroField = new JTextField(20);
                panel.add(new JLabel("Nome do Passageiro:"));
                panel.add(nomePassageiroField);

                panel.add(new JLabel("Companhia:"));
                panel.add(companhiaComboBox);

                panel.add(new JLabel("Rota de Origem:"));
                panel.add(cidadeOrigemComboBox);

                JComboBox<Onibus> onibusComboBox2 = new JComboBox<>(new Onibus[0]);
                panel.add(new JLabel("Ônibus:"));
                panel.add(onibusComboBox2);

                if (!control.listarCompanhias().isEmpty()) {
                    companhiaComboBox.setSelectedIndex(0);
                }

                if (!model.listarRotasValidas().isEmpty()) {
                    cidadeOrigemComboBox.setSelectedIndex(0);
                }

                cidadeOrigemComboBox.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Cidade cidadeOrigemSelecionada = (Cidade) cidadeOrigemComboBox.getSelectedItem();
                        Companhia companhiaSelecionada = (Companhia) companhiaComboBox.getSelectedItem();
                        List<Onibus> onibusDisponiveis = control.listarOnibusDisponiveisParaRota(control.listarOnibus(), cidadeOrigemSelecionada, companhiaSelecionada);
                        DefaultComboBoxModel<Onibus> onibusModel = new DefaultComboBoxModel<>(onibusDisponiveis.toArray(new Onibus[0]));
                        onibusComboBox2.setModel(onibusModel);
                    }
                });

                JButton salvarButton = new JButton("Salvar");
                panel.add(salvarButton);

                salvarButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String nomePassageiro = nomePassageiroField.getText();
                        Companhia companhiaSelecionada = (Companhia) companhiaComboBox.getSelectedItem();
                        Cidade cidadeOrigemSelecionada = (Cidade) cidadeOrigemComboBox.getSelectedItem();
                        Onibus onibusEscolhido = (Onibus) onibusComboBox2.getSelectedItem();

                        if (nomePassageiro.isEmpty() || companhiaSelecionada == null || cidadeOrigemSelecionada == null || onibusEscolhido == null) {
                            JOptionPane.showMessageDialog(null, "Preencha todas as informações necessárias.", "ERRO", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            Passageiro passageiro = new Passageiro(nomePassageiro, companhiaSelecionada, cidadeOrigemSelecionada, onibusEscolhido);
                            cadastro.cadastrarPassageiro(passageiro);
                            JOptionPane.showMessageDialog(null, "Passageiro cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                            novaJanela.dispose();
                        }
                    }
                });

                if (!control.listarCompanhias().isEmpty()) {
                    companhiaComboBox.setSelectedIndex(0);
                }

                if (!model.listarRotasValidas().isEmpty()) {
                    cidadeOrigemComboBox.setSelectedIndex(0);
                }

                Cidade cidadeOrigemSelecionada = (Cidade) cidadeOrigemComboBox.getSelectedItem();
                Companhia companhiaSelecionada = (Companhia) companhiaComboBox.getSelectedItem();
                List<Onibus> onibusDisponiveis = control.listarOnibusDisponiveisParaRota(control.listarOnibus(), cidadeOrigemSelecionada, companhiaSelecionada);
                DefaultComboBoxModel<Onibus> onibusModel = new DefaultComboBoxModel<>(onibusDisponiveis.toArray(new Onibus[0]));
                onibusComboBox2.setModel(onibusModel);

                novaJanela.pack();
                novaJanela.setLocationRelativeTo(frame);
                novaJanela.setVisible(true);
                }
                else{               
                if(companhias.isEmpty()&& rotasOnibus.isEmpty() && onibus.isEmpty()){
                JOptionPane.showMessageDialog(null, "Não há rotas, onibus e companhias cadastradas", "ERRO", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(onibus.isEmpty() && companhias.isEmpty()){
                JOptionPane.showMessageDialog(null, "Não há onibus e companhias cadastradas", "ERRO", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(rotasOnibus.isEmpty() && onibus.isEmpty()){
                JOptionPane.showMessageDialog(null, "Não há rotas e onibus cadastrados", "ERRO", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(rotasOnibus.isEmpty() && companhias.isEmpty()){
                JOptionPane.showMessageDialog(null, "Não há rotas e companhia cadastradas", "ERRO", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(onibus.isEmpty()){
                JOptionPane.showMessageDialog(null, "Não há onibus cadastrados", "ERRO", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(rotasOnibus.isEmpty()){
                JOptionPane.showMessageDialog(null, "Não há rotas cadastradas", "ERRO", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                JOptionPane.showMessageDialog(null, "Não há companhias cadastradas", "ERRO", JOptionPane.INFORMATION_MESSAGE);                
                }                    
                }    
            }
        });
        
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    List<Companhia> companhias = control.listarCompanhias();
                    List<Cidade> rotasOnibus = model.listarRotasValidas();
                    JComboBox<Companhia> companhiaComboBox = new JComboBox<>(companhias.toArray(new Companhia[0]));
                    JComboBox<Cidade> rotaComboBox = new JComboBox<>(rotasOnibus.toArray(new Cidade[0]));

                if (companhias != null && !companhias.isEmpty() && rotasOnibus != null && !rotasOnibus.isEmpty()) {                                  
                    JDialog novaJanela = new JDialog(frame, "Cadastro de Ônibus", true);
                    novaJanela.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    novaJanela.setLayout(new BorderLayout());

                    JPanel panel = new JPanel();
                    novaJanela.add(panel);

                    JTextField nomeOnibusField = new JTextField(20);
                    panel.add(new JLabel("Nome do Ônibus:"));
                    panel.add(nomeOnibusField);

                    panel.add(new JLabel("Companhia:"));
                    panel.add(companhiaComboBox);
                    
                    panel.add(new JLabel("Rota:"));
                    panel.add(rotaComboBox);

                    JButton salvarButton = new JButton("Salvar");
                    panel.add(salvarButton);

                    salvarButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String nomeOnibus = nomeOnibusField.getText();
                            Companhia companhiaSelecionada = (Companhia) companhiaComboBox.getSelectedItem();
                            Cidade rotaSelecionada = (Cidade) rotaComboBox.getSelectedItem();

                            if (!nomeOnibus.isEmpty() && companhiaSelecionada != null && rotaSelecionada != null) {
                                Onibus onibus = new Onibus(nomeOnibus, rotaSelecionada);
                                onibus.setCompanhia(companhiaSelecionada);
                                cadastro.cadastrarOnibus(onibus);
                                JOptionPane.showMessageDialog(null, "Ônibus cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                                novaJanela.dispose();
                            } else {
                                JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente.", "ERRO", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    });

                    novaJanela.pack();
                    novaJanela.setLocationRelativeTo(frame);
                    novaJanela.setVisible(true);
                }
                else{                
                if(companhias.isEmpty()&& rotasOnibus.isEmpty()){
                JOptionPane.showMessageDialog(null, "Não há rotas e companhias cadastradas", "ERRO", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(rotasOnibus.isEmpty()){
                JOptionPane.showMessageDialog(null, "Não há rotas cadastradas", "ERRO", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                JOptionPane.showMessageDialog(null, "Não há companhias cadastradas", "ERRO", JOptionPane.INFORMATION_MESSAGE);                
                }                
                }
            }
        });

        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JDialog novaJanela = new JDialog(frame, "Cadastro de Rota", true);
                novaJanela.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                novaJanela.setLayout(new BorderLayout());

                JPanel panel = new JPanel();
                novaJanela.add(panel);

                JTextField nomeRotaField = new JTextField(12);
                JTextField origemField = new JTextField(12);
                JTextField destinoField = new JTextField(12);
                JTextField tempoPercursoField = new JTextField(4);
                JTextField tarifaPassagemField = new JTextField(4);

                panel.add(new JLabel("Nome da Rota:"));
                panel.add(nomeRotaField);
                panel.add(new JLabel("Origem:"));
                panel.add(origemField);
                panel.add(new JLabel("Destino:"));
                panel.add(destinoField);
                panel.add(new JLabel("Tempo de Percurso (minutos):"));
                panel.add(tempoPercursoField);
                panel.add(new JLabel("Tarifa da Passagem:"));
                panel.add(tarifaPassagemField);

                JButton salvarButton = new JButton("Salvar");
                panel.add(salvarButton);

                salvarButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String nomeRota = nomeRotaField.getText();
                        String origem = origemField.getText();
                        String destino = destinoField.getText();
                        int tempoPercurso = Integer.parseInt(tempoPercursoField.getText());
                        float tarifaPassagem = Float.parseFloat(tarifaPassagemField.getText());

                        Cidade origemRotaCidade = new Cidade(nomeRota, origem, destino, tempoPercurso, tarifaPassagem);
                        
                        //padrão de projeto - Adapter (chamada da nova função o qual 
                        //onClick (sendo esse o metodo cadastrarRota)da interface model, porém para uma interface que os botões entendem.
                        //modelAdapter.onClick(origemRotaCidade);
                        model.cadastrarRota2(origemRotaCidade);
                        novaJanela.dispose();
                    }
                });

                novaJanela.pack();
                novaJanela.setLocationRelativeTo(frame);
                novaJanela.setVisible(true);
            }
        });

        button5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Passageiro> passageiros = control.listarPassageiros();            
                if (passageiros != null && !passageiros.isEmpty()) {            
                ListaPassageirosFrame listaPassageirosDialog = new ListaPassageirosFrame(frame, passageiros);
                listaPassageirosDialog.setVisible(true);                        
                }
                else{
                JOptionPane.showMessageDialog(null, "Não há passageiros cadastrados", "ERRO", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        button6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Onibus> onibus = control.listarOnibus();                
                if (onibus != null && !onibus.isEmpty()) {
                ListaOnibusFrame ListaOnibusFrame = new ListaOnibusFrame(frame, onibus);
                ListaOnibusFrame.setVisible(true);
                }
                else{
                JOptionPane.showMessageDialog(null, "Não há onibus cadastrados", "ERRO", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        button7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Companhia> companhias = control.listarCompanhias();
                if (companhias != null && !companhias.isEmpty()) {
                ListaCompanhiasFrame ListaCompanhiasFrame = new ListaCompanhiasFrame(frame, companhias);
                ListaCompanhiasFrame.setVisible(true);
                }
                else{
                JOptionPane.showMessageDialog(null, "Não há companhias cadastrados", "ERRO", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        button8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Cidade> rotasCadastradas = model.listarRotasValidas();
                if (rotasCadastradas != null && !rotasCadastradas.isEmpty()) {
                ListaRotasFrame ListaRotasFrame  = new ListaRotasFrame (frame, rotasCadastradas);
                ListaRotasFrame.setVisible(true);
                }
                else{
                JOptionPane.showMessageDialog(null, "Não há rotas cadastrados", "ERRO", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        button9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                emitirPassagemGUI(control,frame);
            }
        });

        button10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (gerado > 0) {
                    JDialog listaPassagensFrame = new JDialog(frame, "Lista de Passagens Emitidas", true);
                    listaPassagensFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    listaPassagensFrame.setLayout(new GridLayout(4, 4));

                    List<Passagem> passagensEmitidas = PassagemDAO.listarPassagensEmitidas(control);

                    if (passagensEmitidas.isEmpty()&& passagensEmitidas == null) {
                        JTextArea textArea = new JTextArea("Nenhuma passagem emitida.");
                        textArea.setEditable(false);
                        listaPassagensFrame.add(textArea);
                    } else {
                        for (Passagem passagem : passagensEmitidas) {
                            JTextArea textArea = new JTextArea();
                            textArea.setEditable(false);

                            textArea.append("ID da Passagem: " + passagem.getId() + "\n");

                            if (passagem.getPassageiro() != null) {
                                textArea.append("Nome do Passageiro: " + passagem.getPassageiro().getNome() + "\n");
                            } else {
                                textArea.append("Nome do Passageiro: Passageiro não especificado\n");
                            }

                            if (passagem.getOnibus() != null) {
                                textArea.append("Ônibus: " + passagem.getOnibus().getNome() + "\n");
                            } else {
                                textArea.append("Ônibus: Ônibus não especificado\n");
                            }

                            if (passagem.getOnibus().getCidade() != null) {
                                textArea.append("Origem: " + passagem.getOnibus().getCidade().getOrigem() + "\n");
                                textArea.append("Destino: " + passagem.getOnibus().getCidade().getDestino() + "\n");
                                textArea.append("Tempo de Viagem: " + passagem.getOnibus().getCidade().calcularTempoDeViagemComAcidente() + " minutos\n");
                                textArea.append("Valor da Passagem: R$" + passagem.getOnibus().getCidade().getPrecoBase() * passagem.getOnibus().getCompanhia().getTarifa() + "\n");
                            } else {
                                textArea.append("Cidade: Cidade não especificada\n");
                            }
                        listaPassagensFrame.add(textArea);
                        }
                    }

                    listaPassagensFrame.pack();
                    listaPassagensFrame.setLocationRelativeTo(frame);
                    listaPassagensFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Carregue os Dados para mostrar as passagens emitidas", "ERRO", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        button11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removerOnibusGUI(cadastro, frame);
            }
        });

        button12.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deletarPassageiroGUI(cadastro,frame);
            }
        });

        button13.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deletarRotaGUI(model, cadastro, frame);
            }
        });

        button14.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removerCompanhiaGUI(control, cadastro, frame);
            }
        });
        
        button15.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                    gerado = gerado + 1;
                
                    List<Companhia> companhiasList = CompanhiaDAO.listarCompanhias(control);
                    
                    for (Companhia companhia : companhiasList) {
                        control.cadastrarCompanhia(companhia.getNome());
                    }

                    List<Cidade> cidadeList = RotaDAO.listarRotas(model);
                    
                    List<Onibus> OnibusList = OnibusDAO.listarBus(cadastro, model, control);

                    for (Onibus onibus : OnibusList) {
                        cadastro.cadastrarOnibus(onibus);
                    }
                    
                    for (Cidade cidade : cidadeList) {
                        model.cadastrarRota2(cidade);
                    }
                    
                    List<Passageiro> passageirosList = PassageirosDAO.listarPas(cadastro, model, control);
                   
                    for (Passageiro passageiro : passageirosList) {
                        cadastro.cadastrarPassageiro(passageiro);
                    }                
                JOptionPane.showMessageDialog(null, "Dados carregados com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);                                                 
            }
        });
        
        button16.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            gerado = gerado + 1;

            Cidade origemRotaCidade1 = new Cidade("Pertinho", "Apucarana", "Londrina", 10, 50);
            Cidade origemRotaCidade2 = new Cidade("Meio Termo", "Apucarana", "Ponta Grossa", 20, 100);
            Cidade origemRotaCidade3 = new Cidade("Longinho", "Apucarana", "Curitiba", 30, 200);

            model.cadastrarRota2(origemRotaCidade1);
            model.cadastrarRota2(origemRotaCidade2);
            model.cadastrarRota2(origemRotaCidade3);                

            Companhia companhia1 = new Companhia("Garcia", 1.2); 
            Companhia companhia2 = new Companhia("Nordeste", 1.1);          

            cadastro.cadastrarCompanhia(companhia1);
            cadastro.cadastrarCompanhia(companhia2);

            Onibus onibus1 = new Onibus("Busauumm", model.listarRotasValidas().get(0));
            Onibus onibus2 = new Onibus("Quebradeira", model.listarRotasValidas().get(1));
            Onibus onibus3 = new Onibus("Filezinn", model.listarRotasValidas().get(2));
            Onibus onibus4 = new Onibus("Furaduuu", model.listarRotasValidas().get(0));
            Onibus onibus5 = new Onibus("Turismooo", model.listarRotasValidas().get(1));
            Onibus onibus6 = new Onibus("Executivo", model.listarRotasValidas().get(2));

            onibus1.setCompanhia(companhia1);
            onibus2.setCompanhia(companhia1);
            onibus3.setCompanhia(companhia1);
            onibus4.setCompanhia(companhia2);
            onibus5.setCompanhia(companhia2);
            onibus6.setCompanhia(companhia2);

            cadastro.cadastrarOnibus(onibus1);
            cadastro.cadastrarOnibus(onibus2);
            cadastro.cadastrarOnibus(onibus3);
            cadastro.cadastrarOnibus(onibus4);
            cadastro.cadastrarOnibus(onibus5);
            cadastro.cadastrarOnibus(onibus6);

            Onibus onibusEscolhido1 = null;
            Onibus onibusEscolhido2 = null;

            List<Onibus> onibusDisponiveis1 = control.listarOnibusDisponiveisParaRota(control.listarOnibus(), model.listarRotasValidas().get(0), companhia1);
            if (!onibusDisponiveis1.isEmpty()) {
                onibusEscolhido1 = onibusDisponiveis1.get(0);
            }

            List<Onibus> onibusDisponiveis2 = control.listarOnibusDisponiveisParaRota(control.listarOnibus(), model.listarRotasValidas().get(1), companhia2);
            if (!onibusDisponiveis2.isEmpty()) {
                onibusEscolhido2 = onibusDisponiveis2.get(0);
            }

            Passageiro passageiro1 = new Passageiro("Passageiro1", companhia1, model.listarRotasValidas().get(0), onibusEscolhido1);
            Passageiro passageiro2 = new Passageiro("Passageiro2", companhia2, model.listarRotasValidas().get(1), onibusEscolhido2);

            control.cadastrarPassageiro(passageiro1.getNome(), passageiro1.getCompanhia(), passageiro1.getRotaEscolhida(), passageiro1.getOnibusEscolhido());
            control.cadastrarPassageiro(passageiro2.getNome(), passageiro2.getCompanhia(), passageiro2.getRotaEscolhida(), passageiro2.getOnibusEscolhido());

            JOptionPane.showMessageDialog(null, "Dados carregados com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
    });
        

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    verificarOpcaoSelecionada();
                    int result = JOptionPane.showConfirmDialog(frame, "Deseja realmente sair?", "Confirmação", JOptionPane.YES_NO_OPTION);

                    if (result == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                } catch (OpcaoNaoSelecionadaException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        
        frame.setVisible(true);
    }
     
    private static void verificarOpcaoSelecionada() throws OpcaoNaoSelecionadaException {
        if (gerado == 0) {
            throw new OpcaoNaoSelecionadaException("Carregue os dados antes de fechar a aplicação.");
        }
    }    
    
    private static void emitirPassagemGUI(ControlCadastro control, JFrame frame) {
    JDialog emitirPassagemFrame = new JDialog(frame, "Emissão de Passagens", true);
    emitirPassagemFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    emitirPassagemFrame.setSize(600, 800);
    emitirPassagemFrame.setLayout(new FlowLayout());

    List<Onibus> listarOnibusComPassageiros = new ArrayList<>();
    List<Onibus> onibus = control.listarOnibus();

    for (Onibus bus : onibus) {
        if (!control.listarPassageirosPorOnibus(bus).isEmpty()) {
            listarOnibusComPassageiros.add(bus);
        }
    }

    if (listarOnibusComPassageiros.isEmpty()) {
        System.out.println();
        JOptionPane.showMessageDialog(null, "Não existem ônibus com passageiros cadastrados. Cadastre passageiros em um ônibus antes de emitir uma passagem.", "ERRO", JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    String[] onibusComPassageirosNomes = listarOnibusComPassageiros.stream().map(Onibus::getNome).toArray(String[]::new);
    JComboBox<String> onibusComboBox = new JComboBox<>(onibusComPassageirosNomes);

    JComboBox<String> passageiroComboBox = new JComboBox<>();

    onibusComboBox.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            int selectedOnibusIndex = onibusComboBox.getSelectedIndex();
            if (selectedOnibusIndex >= 0) {
                Onibus onibusEscolhido = listarOnibusComPassageiros.get(selectedOnibusIndex);
                List<Passageiro> passageirosNoOnibus = control.listarPassageirosPorOnibus(onibusEscolhido);
                String[] passageirosNomes = passageirosNoOnibus.stream().map(Passageiro::getNome).toArray(String[]::new);
                DefaultComboBoxModel<String> passageiroModel = new DefaultComboBoxModel<>(passageirosNomes);
                passageiroComboBox.setModel(passageiroModel);

                if (!passageirosNoOnibus.isEmpty()) {
                    passageiroComboBox.setSelectedIndex(0);
                }
            }
        }
    });

    if (!listarOnibusComPassageiros.isEmpty()) {
    onibusComboBox.setSelectedIndex(0);
    }    
    
    JButton emitirPassagemButton = new JButton("Emitir Passagem");
    emitirPassagemButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            int selectedOnibusIndex = onibusComboBox.getSelectedIndex();
            int selectedPassageiroIndex = passageiroComboBox.getSelectedIndex();
            if (selectedOnibusIndex >= 0 && selectedPassageiroIndex >= 0) {
                Onibus onibusEscolhido = listarOnibusComPassageiros.get(selectedOnibusIndex);
                Passageiro passageiro = control.listarPassageirosPorOnibus(onibusEscolhido).get(selectedPassageiroIndex);

                int idPassagem = new Random().nextInt(10000);

                float valorPassagem = (float) (onibusEscolhido.getCidade().getPrecoBase() * onibusEscolhido.getCompanhia().getTarifa());

                Passagem novaPassagem = new Passagem(idPassagem, passageiro, onibusEscolhido);

                List<Passagem> passagensEmitidas = new ArrayList<>();
                passagensEmitidas.add(novaPassagem);

                PassagemDAO.salvarPassagem(passagensEmitidas);

                control.listarPassageiros().remove(passageiro);
                JOptionPane.showMessageDialog(null, "Passageiro " + passageiro.getNome() + " removido da lista de passageiros.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                listarOnibusComPassageiros.clear();
                List<Onibus> onibusComPassageiros = control.listarOnibus();

                for (Onibus bus : onibusComPassageiros) {
                    if (!control.listarPassageirosPorOnibus(bus).isEmpty()) {
                        listarOnibusComPassageiros.add(bus);
                    }
                }

                String[] onibusComPassageirosNomes = listarOnibusComPassageiros.stream().map(Onibus::getNome).toArray(String[]::new);
                DefaultComboBoxModel<String> onibusModel = new DefaultComboBoxModel<>(onibusComPassageirosNomes);
                onibusComboBox.setModel(onibusModel);

                JOptionPane.showMessageDialog(null, "Passagem emitida com sucesso.");
                emitirPassagemFrame.dispose(); 
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um ônibus com passageiros e um passageiro.");
            }
        }
    });

    emitirPassagemFrame.add(new JLabel("Escolha um ônibus com passageiros:"));
    emitirPassagemFrame.add(onibusComboBox);
    emitirPassagemFrame.add(new JLabel("Escolha um passageiro:"));
    emitirPassagemFrame.add(passageiroComboBox);
    emitirPassagemFrame.add(emitirPassagemButton);
    emitirPassagemFrame.pack();
    emitirPassagemFrame.setLocationRelativeTo(frame);
    emitirPassagemFrame.setVisible(true);
}

    private static void removerCompanhiaGUI(ControlCadastro control, CadastroPassagem cadastroPassagem, JFrame frame) {
        List<Companhia> companhias = control.listarCompanhias();

        if (companhias.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não existem companhias cadastradas para remover.");
            return;
        }

        String[] companhiasNomes = companhias.stream().map(Companhia::getNome).toArray(String[]::new);
        
        int newSize = companhiasNomes.length - 2;
        String[] novascomp = new String[newSize];
        System.arraycopy(companhiasNomes, 2, novascomp, 0, newSize);
        companhiasNomes = novascomp;
        
        JComboBox<String> companhiaComboBox = new JComboBox<>(companhiasNomes);
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Escolha a companhia para remover:"));
        panel.add(companhiaComboBox);

        int option = JOptionPane.showConfirmDialog(null, panel, "Escolha a companhia para remover", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            int selectedCompanhiaIndex = companhiaComboBox.getSelectedIndex();
            if (selectedCompanhiaIndex >= 0) {
                Companhia companhiaRemover = companhias.get(selectedCompanhiaIndex);

                List<Onibus> onibusDaCompanhia = cadastroPassagem.listarOnibusPorCompanhia(companhiaRemover);

                if (onibusDaCompanhia.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "A companhia " + companhiaRemover.getNome() + " não possui ônibus associados.");
                } else {
                    StringBuilder message = new StringBuilder("Ônibus associados à companhia " + companhiaRemover.getNome() + ":\n");
                    for (int i = 0; i < onibusDaCompanhia.size(); i++) {
                        message.append(i + 1).append(". ").append(onibusDaCompanhia.get(i).getNome()).append("\n");
                    }

                    int confirm = JOptionPane.showConfirmDialog(null, message + "\nDeseja realmente remover a companhia e todos os seus ônibus associados?", "Confirmação", JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        cadastroPassagem.removerCompanhiaEOnibus(companhiaRemover, onibusDaCompanhia);

                        JOptionPane.showMessageDialog(null, "Companhia " + companhiaRemover.getNome() + " e seus ônibus associados foram removidos com sucesso.");
                    } else {
                        JOptionPane.showMessageDialog(null, "A remoção foi cancelada.");
                    }
                }
            }
        }
    }
     
    private static void deletarRotaGUI(ModelCadastro model, CadastroPassagem cadastroPassagem, JFrame frame) {
        List<Cidade> rotas = model.listarRotasValidas();

        if (rotas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não existem rotas cadastradas para remover.", "ERRO", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JDialog deletarRotaFrame = new JDialog(frame, "Remover Rota", true);
        deletarRotaFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        deletarRotaFrame.setLayout(new FlowLayout());

        String[] rotaNomes = rotas.stream().map(Cidade::getNome).skip(3).toArray(String[]::new);
        
        JComboBox<String> rotaComboBox = new JComboBox<>(rotaNomes);
        deletarRotaFrame.add(new JLabel("Selecione a rota a ser removida:"));
        deletarRotaFrame.add(rotaComboBox);

        JButton removerRotaButton = new JButton("Remover Rota");
        deletarRotaFrame.add(removerRotaButton);

        removerRotaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRotaIndex = rotaComboBox.getSelectedIndex();
                if (selectedRotaIndex >= 0) {
                    Cidade rotaRemover = rotas.get(selectedRotaIndex + 3);
                    model.removERROta(rotaRemover);
                    rotaComboBox.removeItemAt(selectedRotaIndex);

                    cadastroPassagem.removerOnibusPorRota(rotaRemover);

                    JOptionPane.showMessageDialog(null, "Rota " + rotaRemover.getNome() + " removida com sucesso.");
                    deletarRotaFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma rota para remover.");
                }
            }
        });

        deletarRotaFrame.pack();
        deletarRotaFrame.setLocationRelativeTo(frame);
        deletarRotaFrame.setVisible(true);
    }
     
    private static void deletarPassageiroGUI(CadastroPassagem cadastro, JFrame frame) {
        List<Passageiro> passageiros = cadastro.listarPassageiros();

        if (passageiros.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não existem passageiros cadastrados para remover.", "ERRO", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JDialog deletarPassageiroFrame = new JDialog(frame, "Remover Passageiro", true);
        deletarPassageiroFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        deletarPassageiroFrame.setLayout(new FlowLayout());

        String[] passageirosNomes = passageiros.stream().map(Passageiro::getNome).toArray(String[]::new);
        
        JComboBox<String> passageiroComboBox = new JComboBox<>(passageirosNomes);
        deletarPassageiroFrame.add(new JLabel("Selecione o passageiro a ser removido:"));
        deletarPassageiroFrame.add(passageiroComboBox);

        JButton removerPassageiroButton = new JButton("Remover Passageiro");
        deletarPassageiroFrame.add(removerPassageiroButton);

        removerPassageiroButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedPassageiroIndex = passageiroComboBox.getSelectedIndex();
                if (selectedPassageiroIndex >= 0) {
                    Passageiro passageiroRemover = passageiros.get(selectedPassageiroIndex);
                    cadastro.removerPassageiro(passageiroRemover);
                    passageiroComboBox.removeItemAt(selectedPassageiroIndex);
                    JOptionPane.showMessageDialog(null, "Passageiro removido com sucesso.");
                    deletarPassageiroFrame.dispose(); 
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um passageiro para remover.");
                }
            }
        });

        deletarPassageiroFrame.pack();
        deletarPassageiroFrame.setLocationRelativeTo(frame);
        deletarPassageiroFrame.setVisible(true);
    }

    private static void removerOnibusGUI(CadastroPassagem cadastroPassagem, JFrame frame) {
        List<Onibus> onibus = cadastroPassagem.listarOnibus();

        if (onibus.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não existem ônibus cadastrados para remover.", "ERRO", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JDialog removerOnibusFrame = new JDialog(frame, "Remover Ônibus", true);
        removerOnibusFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        removerOnibusFrame.setLayout(new FlowLayout());

        String[] onibusNomes = onibus.stream().map(Onibus::getNome).toArray(String[]::new);
        
        int newSize = onibusNomes.length - 6;
        String[] novosonibus = new String[newSize];
        System.arraycopy(onibusNomes, 6, novosonibus, 0, newSize);
        onibusNomes = novosonibus;
        
        JComboBox<String> onibusComboBox = new JComboBox<>(onibusNomes);
        removerOnibusFrame.add(new JLabel("Selecione o ônibus a ser removido:"));
        removerOnibusFrame.add(onibusComboBox);

        JButton removerOnibusButton = new JButton("Remover Ônibus");
        removerOnibusFrame.add(removerOnibusButton);

        removerOnibusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedOnibusIndex = onibusComboBox.getSelectedIndex();
                if (selectedOnibusIndex >= 0) {
                    Onibus onibusRemover = onibus.get(selectedOnibusIndex);
                    cadastroPassagem.removerOnibus(onibusRemover);
                    onibusComboBox.removeItemAt(selectedOnibusIndex);
                    JOptionPane.showMessageDialog(null, "Ônibus removido com sucesso.");
                    removerOnibusFrame.dispose(); 
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um ônibus para remover.");
                }
            }
        });

        removerOnibusFrame.pack();
        removerOnibusFrame.setLocationRelativeTo(frame);
        removerOnibusFrame.setVisible(true);
    }      
    
    public static class ListaRotasFrame extends JDialog {
        public ListaRotasFrame(Frame owner, List<Cidade> rotas) {
            super(owner, "Lista de Rotas", true);
            setSize(400, 300);
            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            JPanel panel = new JPanel(new FlowLayout());
            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);

            for (Cidade rota : rotas) {
                textArea.append("Rota: " + rota.getNome() +
                        ", Origem: " + rota.getOrigem() +
                        ", Destino: " + rota.getDestino() +
                        ", Tempo de Percurso: " + rota.getTempoPercurso() + " minutos" +
                        ", Tarifa da Passagem: R$" + rota.getPrecoBase() + "\n");
            }

            textArea.setRows(rotas.size() + 1);

            JScrollPane scrollPane = new JScrollPane(textArea);
            panel.add(scrollPane);

            getContentPane().add(panel);
            pack();
            setLocationRelativeTo(owner);
        }
    }

    public static class ListaOnibusFrame extends JDialog {
        public ListaOnibusFrame(Frame owner, List<Onibus> onibus) {
            super(owner, "Lista de Ônibus", true);
            setSize(400, 300);
            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            JPanel panel = new JPanel(new FlowLayout());
            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);

            for (Onibus bus : onibus) {
                textArea.append("Nome: " + bus.getNome() +
                        ", Origem: " + bus.getCidade().getOrigem() +
                        ", Destino: " + bus.getCidade().getDestino() +
                        ", Companhia: " + bus.getCompanhia().getNome() + "\n");
            }

            textArea.setRows(onibus.size() + 1);

            JScrollPane scrollPane = new JScrollPane(textArea);
            panel.add(scrollPane);

            getContentPane().add(panel);
            pack();
            setLocationRelativeTo(owner);
        }
    }

    public static class ListaPassageirosFrame extends JDialog {
        public ListaPassageirosFrame(Frame owner, List<Passageiro> passageiros) {
            super(owner, "Lista de Passageiros", true);
            setSize(400, 300);
            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            JPanel panel = new JPanel(new FlowLayout());
            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);

            for (Passageiro passageiro : passageiros) {
                textArea.append("Nome: " + passageiro.getNome() +
                        ", Companhia: " + passageiro.getCompanhia().getNome() +
                        ", Rota Escolhida: " + passageiro.getRotaEscolhida().getNome() +
                        ", Ônibus Escolhido: " + passageiro.getOnibusEscolhido().getNome() + "\n");
            }

            textArea.setRows(passageiros.size() + 1);

            JScrollPane scrollPane = new JScrollPane(textArea);
            panel.add(scrollPane);
            getContentPane().add(panel);
            pack();
            setLocationRelativeTo(owner);
        }
    }
    
    public static class ListaCompanhiasFrame extends JDialog {
        public ListaCompanhiasFrame(Frame owner, List<Companhia> companhias) {
            super(owner, "Lista de Companhias de Ônibus", true);
            setSize(400, 300);
            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            JPanel panel = new JPanel(new FlowLayout());
            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);

            for (Companhia companhia : companhias) {
                textArea.append("Nome da Companhia: " + companhia.getNome() + " - Tarifa: " + companhia.getTarifa() + "\n");
            }

            textArea.setRows(companhias.size() + 1);

            JScrollPane scrollPane = new JScrollPane(textArea);
            panel.add(scrollPane);

            getContentPane().add(panel);
            pack();
            setLocationRelativeTo(owner);
        }
    }

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });     
    }
}
