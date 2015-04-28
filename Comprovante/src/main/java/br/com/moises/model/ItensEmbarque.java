/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.Min;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author MOISES
 */
@Entity
@Table(name = "tbl_itens_embarque")
public class ItensEmbarque implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ite_id")
    private Long id;

    @Column(name = "ite_nota_fiscal")
    private String notaFiscal;

    @Column(name = "ite_quantidade_volume")
    @Min(value = 1,message = "Mínimo de um volume")
    private double quantidadeVolume;

    @JoinColumn(name = "ite_cliente_id")
    @ManyToOne()
    private Cliente cliente;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "ite_data_inclusao")
    private Date dataInclusao;
    @ManyToOne
    @JoinColumn(name = "ite_embarque_id")
    @Cascade(CascadeType.SAVE_UPDATE)
    private Embarque embarque;
    
    

    public ItensEmbarque() {
        cliente = new Cliente();
        embarque = new Embarque();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(String notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public double getQuantidadeVolume() {
        return quantidadeVolume;
    }

    public void setQuantidadeVolume(double quantidadeVolume) {
        this.quantidadeVolume = quantidadeVolume;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItensEmbarque other = (ItensEmbarque) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    public Embarque getEmbarque() {
        return embarque;
    }

    public void setEmbarque(Embarque embarque) {
        this.embarque = embarque;
    }

}
