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
        super("Alunos matriculados: ");

        //lista de pessoas
        ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
        pessoas.add(new Pessoa("Benevenuto", 1234, 213123, "Artes", "Noturno")); 

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
        JTable alunoTable = new JTable();
        alunoTable.setModel(ptm);

        //botão de registrar e ação de registrar
        JButton registrar = new JButton("Registrar");

        registrar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ptm.addPessoa(registraPessoas(nome.getText(), Integer.parseInt(cpf.getText()), Integer.parseInt(matricula.getText()), materia.getText(), turno.getText()));      
                cleaner();
            }
            
        });

        //botao de editar, depois de alterar campo, double click salva progresso
        JButton editar = new JButton("Editar");

        editar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2){
                    ptm.getPessoa(alunoTable.getSelectedRow()).setNome((nome.getText()));
                    ptm.getPessoa(alunoTable.getSelectedRow()).setCpf(Integer.parseInt(cpf.getText()));
                    ptm.getPessoa(alunoTable.getSelectedRow()).setMatricula(Integer.parseInt(matricula.getText()));
                    ptm.getPessoa(alunoTable.getSelectedRow()).setMateria(materia.getText());
                    ptm.getPessoa(alunoTable.getSelectedRow()).setTurno(turno.getText());

                    ptm.fireTableDataChanged();
                    
                    cleaner();
                }
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
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }
        });

        //limpa os campos
        JButton cancelar = new JButton("Cancelar");

        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                cleaner();
            }
        });

        //seleciona os itens e mostra nas labels
        alunoTable.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                Pessoa p = ptm.getPessoa(alunoTable.getSelectedRow());
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
        alunoTable.addKeyListener(new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				ptm.dellPessoa(alunoTable.getSelectedRow());
                cleaner();
			}

			@Override
            public void keyReleased(KeyEvent e){}
            
        });

        p1.add(registrar);
        p1.add(editar);
        p1.add(cancelar);

        getContentPane().add(p1, BorderLayout.NORTH);

        add(new JScrollPane(alunoTable), BorderLayout.CENTER);
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
        new AlunoTable();
    }
}
