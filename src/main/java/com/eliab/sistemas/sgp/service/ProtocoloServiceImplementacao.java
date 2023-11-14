package com.eliab.sistemas.sgp.service;

import com.eliab.sistemas.sgp.model.EnumStatus;
import com.eliab.sistemas.sgp.model.Protocolo;
import com.eliab.sistemas.sgp.model.Requerente;
import com.eliab.sistemas.sgp.model.format.Formatado;
import com.eliab.sistemas.sgp.repository.ProtocoloRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Optional;

@Service
public class ProtocoloServiceImplementacao implements ProtocoloService{

    @Autowired
    private ProtocoloRepository protocoloRepository;

    @Autowired
    private RequerenteService requerenteService;




    @Override
    public Iterable<Protocolo> buscarTodos() {
        return protocoloRepository.findAll();
    }

    @Override
    public Protocolo buscarPorId(@PathVariable Long id) {
        Optional<Protocolo> busca = protocoloRepository.findById(id);
        return busca.get();
    }


    @Override
    public Protocolo salvar(Protocolo protocolo) throws ConstraintViolationException {
            Requerente requerenteSalvo = requerenteService.salvar(protocolo.getRequerente());
             protocolo.setRequerente(requerenteSalvo);

             formatarData(protocolo);
                                              //método 'count()' conta número de linhas em uma tabela
             formatarProtocolo(protocolo, (protocoloRepository.count()) + 1);

        return protocoloRepository.save(protocolo);
    }

    @Override
    public void atualizar(Long id, Protocolo protocolo) {

    }

    @Override
    public void deletar(Long id) {

    }

    @Override
    public EnumStatus mudarStatus(Long id, EnumStatus status) {
        Optional<Protocolo> protocolo = protocoloRepository.findById(id);
        Protocolo obj = protocolo.get();

        if (status == EnumStatus.DEFERIDO)
           status.deferir(id, status);

        else if(status == EnumStatus.INDEFERIDO)
            status.indeferir(id,status);

        obj.setStatus(status);
        protocoloRepository.save(obj);
        return status;
    }

    public String formatarData(Protocolo protocolo) {

        DateTimeFormatter dataTime = DateTimeFormatter
                .ofPattern("yyyy-MM-dd")
                .withResolverStyle(ResolverStyle.STRICT);
        LocalDateTime dataNow = LocalDateTime.now();
        protocolo.setData(dataNow.format(dataTime));
        return protocolo.getData();
    }

    public String formatarProtocolo(Protocolo protocolo, Long id) {
        Formatado formatter = new Formatado();

        protocolo.setProtocolo(formatter.getFormatado());
        String idFormatado = String.format("%9s", id).replace(" ", "0");
        protocolo.setProtocolo(protocolo.getProtocolo() + idFormatado);
        return protocolo.getProtocolo();
    }


    /*@Override
    public EnumStatus deferir(Long id, EnumStatus status){
        obj.setStatus(EnumStatus.DEFERIDO);
        protocoloRepository.save(obj);


        return EnumStatus.DEFERIDO;
    }

    @Override
    public EnumStatus indeferir(Long id, EnumStatus status){

        Optional<Protocolo> protocolo = protocoloRepository.findById(id);
        Protocolo obj = protocolo.get();
        obj.setStatus(EnumStatus.INDEFERIDO);
        protocoloRepository.save(obj);
        return EnumStatus.INDEFERIDO;
    }

     */

    /*

        String strContador = String.valueOf(zeros + id);

        if (nCaracteresDoId == (nCaracteresDoId + 1)) {
            strContador = String.valueOf(zeros.substring(1, numZeros) + id);
        }

        */

    //FORMATAR ID COM 000000

    /*
    public static void main(String[] args) {
        String[] ids = "1 3 76 874 9999 10987655 765432 98765432 9999999".split("\s+");
        for (String id: ids) {
            String dataFormatada = "20230601";
            String idFormatado = String.format("%8s", id).replace(" ", "0");

            String numeroProtocoloFormatado = dataFormatada + idFormatado;
            System.out.println(numeroProtocoloFormatado);
        }
    }

     */

}
