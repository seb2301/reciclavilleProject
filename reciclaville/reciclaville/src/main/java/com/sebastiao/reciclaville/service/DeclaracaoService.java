package com.sebastiao.reciclaville.service;

import com.sebastiao.reciclaville.dto.DeclaracaoRequest;
import com.sebastiao.reciclaville.dto.DeclaracaoResponse;
import com.sebastiao.reciclaville.dto.ItemDeclaracaoResponse;
import com.sebastiao.reciclaville.exception.ResourceNotFoundException;
import com.sebastiao.reciclaville.model.Cliente;
import com.sebastiao.reciclaville.model.Declaracao;
import com.sebastiao.reciclaville.model.ItemDeclaracao;
import com.sebastiao.reciclaville.model.Material;
import com.sebastiao.reciclaville.repository.ClienteRepository;
import com.sebastiao.reciclaville.repository.DeclaracaoRepository;
import com.sebastiao.reciclaville.repository.MaterialRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeclaracaoService {

    private final DeclaracaoRepository declRepo;
    private final ClienteRepository clienteRepo;
    private final MaterialRepository materialRepo;

    public DeclaracaoService(DeclaracaoRepository declRepo,
                             ClienteRepository clienteRepo,
                             MaterialRepository materialRepo) {
        this.declRepo = declRepo;
        this.clienteRepo = clienteRepo;
        this.materialRepo = materialRepo;
    }

    @Transactional
    public DeclaracaoResponse criar(DeclaracaoRequest req) {
        Cliente cliente = clienteRepo.findById(req.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cliente não encontrado: " + req.getClienteId()));

        if (req.getDataInicioPeriodo().isAfter(req.getDataFinalPeriodo())) {
            throw new IllegalArgumentException(
                    "Data inicial não pode ser depois da data final.");
        }

        Declaracao declaracao = new Declaracao();
        declaracao.setCliente(cliente);
        declaracao.setDataDeclaracao(LocalDate.now());
        declaracao.setDataInicioPeriodo(req.getDataInicioPeriodo());
        declaracao.setDataFinalPeriodo(req.getDataFinalPeriodo());
        declaracao = declRepo.save(declaracao);

        double somaMateriais = 0;
        double somaCompensacao = 0;
        List<ItemDeclaracao> itens = new ArrayList<>();

        for (var itemReq : req.getItens()) {
            Material mat = materialRepo.findById(itemReq.getMaterialId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Material não encontrado: " + itemReq.getMaterialId()));

            double toneladasDec = itemReq.getToneladasDeclaradas();
            if (toneladasDec <= 0) {
                throw new IllegalArgumentException(
                        "Toneladas declaradas deve ser maior que zero.");
            }

            double compensacao = toneladasDec * mat.getPercentualCompensacao() / 100.0;

            ItemDeclaracao item = new ItemDeclaracao();
            item.setDeclaracao(declaracao);
            item.setMaterial(mat);
            item.setPercentualCompensacao(mat.getPercentualCompensacao());
            item.setToneladasDeclaradas(toneladasDec);
            item.setToneladasCompensacao(compensacao);

            itens.add(item);

            somaMateriais   += toneladasDec;
            somaCompensacao += compensacao;
        }

        declaracao.setItens(itens);
        declaracao.setTotalMateriais(somaMateriais);
        declaracao.setTotalCompensacao(somaCompensacao);
        Declaracao salvo = declRepo.save(declaracao);

        return toResponse(salvo);
    }

    public List<DeclaracaoResponse> listarTodos() {
        return declRepo.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public DeclaracaoResponse buscarPorId(Long id) {
        Declaracao d = declRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Declaração não encontrada: " + id));
        return toResponse(d);
    }

    public void excluir(Long id) {
        if (!declRepo.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Declaração não encontrada: " + id);
        }
        declRepo.deleteById(id);
    }


    private DeclaracaoResponse toResponse(Declaracao d) {
        DeclaracaoResponse resp = new DeclaracaoResponse();
        resp.setId(d.getId());
        resp.setClienteId(d.getCliente().getId());
        resp.setDataDeclaracao(d.getDataDeclaracao());
        resp.setDataInicioPeriodo(d.getDataInicioPeriodo());
        resp.setDataFinalPeriodo(d.getDataFinalPeriodo());
        resp.setTotalMateriais(d.getTotalMateriais());
        resp.setTotalCompensacao(d.getTotalCompensacao());

        List<ItemDeclaracaoResponse> itensResp = new ArrayList<>();
        for (ItemDeclaracao item : d.getItens()) {
            ItemDeclaracaoResponse ir = new ItemDeclaracaoResponse();
            ir.setId(item.getId());
            ir.setMaterialId(item.getMaterial().getId());
            ir.setMaterialNome(item.getMaterial().getNome());
            ir.setPercentualCompensacao(item.getPercentualCompensacao());
            ir.setToneladasDeclaradas(item.getToneladasDeclaradas());
            ir.setToneladasCompensacao(item.getToneladasCompensacao());
            itensResp.add(ir);
        }
        resp.setItens(itensResp);

        return resp;
    }
}
