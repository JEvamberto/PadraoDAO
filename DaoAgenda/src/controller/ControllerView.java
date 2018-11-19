/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AgendaBDDao;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import model.Agenda;
import model.Modelo;
import model.Observer;

import view.View;

/**
 *
 * @author jose
 */
public class ControllerView implements Observer {

    private Modelo model;
    private View view;
    private int id;

    public ControllerView(Modelo model, View view) {
        this.model = model;
        this.view = view;
        this.model.attach(this);

    }

    @Override
    public void update() {

    }

    public void trataEvento(ActionEvent evt) {
        if ("Inserir".equals(evt.getActionCommand())) {

            this.model.resgistraContato(this.view.getTxtNome().getText(), this.view.getTxtEmail().getText(), this.view.getTxtTelefone().getText());

        }
        if ("Alterar".equals(evt.getActionCommand())) {

            this.model.atualizaContato(this.id, this.view.getTxtNome().getText(), this.view.getTxtEmail().getText(), this.view.getTxtTelefone().getText());

        }
        if ("Excluir".equals(evt.getActionCommand())) {
            this.model.excluir(this.id);

        }

        if ("comboBoxChanged".equals(evt.getActionCommand())) {
            if (((String) this.view.getEscolheSalver().getSelectedItem()).equals("SGBD")) {
                this.model.escolheDao("Bd");
            }

            if (((String) this.view.getEscolheSalver().getSelectedItem()).equals("ARQUIVO")) {
                this.model.escolheDao("file");
            }

        }

    }

    public void trataEventoMouse(MouseEvent evt) {

        if (this.model.getDao() instanceof AgendaBDDao) {
            Agenda agenda = (Agenda) (Object) this.view.getjList1().getSelectedValue();
            this.id = agenda.getId();
            this.view.getTxtEmail().setText(agenda.getEmail());
            this.view.getTxtTelefone().setText(agenda.getTelefone());
            this.view.getTxtNome().setText(agenda.getNome());
        } else {

            String recebe = (String) (Object) this.view.getjList1().getSelectedValue();

            recebe = recebe.replaceAll("id:", "");
            recebe = recebe.replaceAll("Nome:", "");
            recebe = recebe.replaceAll("E-mail:", "");
            recebe = recebe.replaceAll("telefone:", "");

            String no[] = recebe.split(",");

            Agenda agenda = new Agenda();
            this.id = Integer.parseInt(no[0]);
            this.view.getTxtEmail().setText(no[2]);
            this.view.getTxtTelefone().setText(no[3]);
            this.view.getTxtNome().setText(no[1]);

        }
    }

}
