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
        if (protocolo.isPresent()) {
            Protocolo obj = protocolo.get();

            if (obj.getStatus() == EnumStatus.PENDENTE) {

                obj.setStatus(status);
                protocoloRepository.save(obj);

                return status;
            } else {
                throw new IllegalStateException("Não é possível alterar o status de um protocolo já deferido ou indeferido.");
            }
        } else {
            throw new IllegalArgumentException("Protocolo com ID " + id + " não encontrado.");
        }
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
}