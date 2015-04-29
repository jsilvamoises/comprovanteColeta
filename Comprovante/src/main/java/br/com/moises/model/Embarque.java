/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moises.model;

import br.com.moises.enums.StatusEmbarque;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author MOISES
 */
@Entity
@Table(name = "tbl_embarque")
public class Embarque implements Serializable {

    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "emb_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "emb_transportadora_id")
    @ForeignKey(name = "FK_EMBARQUE_TRANSPORTADORA")
    private Transportadora transportadora;

    @Column(name = "emb_data_criacao")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataCriacao;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataEmbarque;

    @Column(name = "emb_nome_usuario")
    private String nomeUsuario;

    @Column(name = "emb_nome_motorista")
    private String nomeMotorista;

    @Column(name = "emb_rg_motorista")
    private String rgMotorista;

    @Column(name = "emb_placa_veiculo")
    private String placaVeiculo;

    @Column(name = "emb_numero_lacre")
    private String numeroLacre;

    @Column(name = "emb_status")
    @Enumerated(EnumType.STRING)
    private StatusEmbarque status;

    @Column(name = "emb_observacao")
    private String observacao;
    
//    @OneToMany(mappedBy = "embarque")
//    @Fetch(FetchMode.JOIN)
//    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
//    private List<ItensEmbarque> itens;

    public Embarque() {
        transportadora = new Transportadora();
       // itens = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Transportadora getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataEmbarque() {
        return dataEmbarque;
    }

    public void setDataEmbarque(Date dataEmbarque) {
        this.dataEmbarque = dataEmbarque;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getNomeMotorista() {
        return nomeMotorista;
    }

    public void setNomeMotorista(String nomeMotorista) {
        this.nomeMotorista = nomeMotorista;
    }

    public String getRgMotorista() {
        return rgMotorista;
    }

    public void setRgMotorista(String rgMotorista) {
        this.rgMotorista = rgMotorista;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public String getNumeroLacre() {
        return numeroLacre;
    }

    public void setNumeroLacre(String numeroLacre) {
        this.numeroLacre = numeroLacre;
    }

    public StatusEmbarque getStatus() {
        return status;
    }

    public void setStatus(StatusEmbarque status) {
        this.status = status;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    
//    public List<ItensEmbarque> getItens() {
//        return itens;
//    }
//
//    public void setItens(List<ItensEmbarque> itens) {
//        this.itens = itens;
//    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final Embarque other = (Embarque) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

}
