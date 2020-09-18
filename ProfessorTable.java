import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class ProfessorTable extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField nome = new JTextField();
    private JTextField cpf = new JTextField();
    private JTextField materia = new JTextField();
    private JTextField matricula = new JTextField();
    private JTextField turno = new JTextField();

    public ProfessorTable() {
        super("Professores registrados: ");

        //lista de pessoas
        ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
        pessoas.add(new Pessoa("Cabo", 1234, 213123, "Teologia", "Noturno")); 
        pessoas.add(new Pessoa("Rogério", 12213, 2112323, "S.I", "Diurno")); 

        //layout
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //painel que fica td em cima 
        JPanel p1 = new JPanel();

        p1.setLayout(new GridLayout(0, 2));

        p1.add(new JLabel("Nome: "));
        p1.add(nome);

        p1.add(new JLabel("CPF: "));
        p1.add(cpf);

        p1.add(new JLabel("Matricula: "));
        p1.add(matricula);

        p1.add(new JLabel("Matéria: "));
        p1.add(materia);

        p1.add(new JLabel("Turno: "));
        p1.add(turno);

        //modelo de tabela pra seguir como Pessoa
        PessoaTableModel ptm = new PessoaTableModel();
        ptm.setPessoas(pessoas);
        JTable professorTable = new JTable();
        professorTable.setModel(ptm);

        //botão de registrar e ação de registrar
        JButton registrar = new JButton("Registrar");
        
        //cor do jbutton
        Color cor = registrar.getBackground();

        registrar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ptm.addPessoa(registraPessoas(nome.getText(), Integer.parseInt(cpf.getText()), Integer.parseInt(matricula.getText()), materia.getText(), turno.getText()));      
                cleaner();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                registrar.setBackground(Color.CYAN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                registrar.setBackground(cor);
            }
        });

        //botao de editar, depois de alterar campo, double click salva progresso
        JButton editar = new JButton("Editar");

        editar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ptm.getPessoa(professorTable.getSelectedRow()).setNome((nome.getText()));
                ptm.getPessoa(professorTable.getSelectedRow()).setCpf(Integer.parseInt(cpf.getText()));
                ptm.getPessoa(professorTable.getSelectedRow()).setMatricula(Integer.parseInt(matricula.getText()));
                ptm.getPessoa(professorTable.getSelectedRow()).setMateria(materia.getText());
                ptm.getPessoa(professorTable.getSelectedRow()).setTurno(turno.getText());
                ptm.fireTableDataChanged();
                    
                cleaner();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                editar.setBackground(Color.CYAN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                editar.setBackground(cor);
            }
        });

        //limpa os campos
        JButton cancelar = new JButton("Cancelar");

        cancelar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cleaner();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                cancelar.setBackground(Color.CYAN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                cancelar.setBackground(cor);
            }
        });

        //seleciona os itens e mostra nas labels
        professorTable.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                Pessoa p = ptm.getPessoa(professorTable.getSelectedRow());
                nome.setText(p.getNome());
                cpf.setText("" + p.getCpf());
                matricula.setText("" + p.getMatricula());
                materia.setText(p.getMateria());
                turno.setText(p.getTurno());
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}

        });

        //deleta 
        professorTable.addKeyListener(new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				ptm.dellPessoa(professorTable.getSelectedRow());
                cleaner();
			}

			@Override
            public void keyReleased(KeyEvent e){}
            
        });

        p1.add(registrar);
        p1.add(editar);
        p1.add(cancelar);

        getContentPane().add(p1, BorderLayout.NORTH);

        add(new JScrollPane(professorTable), BorderLayout.CENTER);
        pack();
        setVisible(true);
    }

    public Pessoa registraPessoas(String nome, int cpf, int matricula, String materia, String turno){
        Pessoa p = new Pessoa(nome, cpf, matricula, materia, turno);
        return p;
    }

    public void cleaner(){
        nome.setText("");
        cpf.setText("");
        materia.setText("");
        matricula.setText("");
        turno.setText("");
    }

    public static void main(String[] args) {
        new ProfessorTable();
    }
}
