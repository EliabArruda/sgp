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
public class ProtocoloServiceImplementacao implements ProtocoloService {

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
        formatarProtocolo(protocolo);
        System.out.println("Protocolo " + protocolo.getProtocolo());

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

        else if (status == EnumStatus.INDEFERIDO)
            status.indeferir(id, status);

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

    public String formatarProtocolo(Protocolo protocolo) {
        if (protocolo.getId() != null) {
            Formatado formatter = new Formatado();
            String idFormatado = String.format("%9s", protocolo.getId()).replace(" ", "0");
            return formatter.getFormatado() + idFormatado;
        }
        return null;


    }
}
