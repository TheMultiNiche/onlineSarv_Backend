package com.example.authapp.service;


import com.example.authapp.entity.Tool;
import com.example.authapp.repository.ToolRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ToolExecutionService {

    private final ToolRepository toolRepository;

    public ToolExecutionService(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    // List all available tools
    public List<Tool> getAllTools() {
        return toolRepository.findAll();
    }

    // Execute tool based on slug
    public String executeTool(String slug, MultipartFile[] files) {
        switch (slug) {
            case "merge-pdf":
                return mergePdf(files);
            case "split-pdf":
                return splitPdf(files);
            case "compress-pdf":
                return compressPdf(files);
//            case "pdf-to-word":
//                return convertPdfToWord(files);
//            case "word-to-pdf":
//                return convertWordToPdf(files);
//            case "pdf-to-image":
//                return convertPdfToImage(files);
//            case "image-to-pdf":
//                return convertImageToPdf(files);
//            case "rotate-pdf":
//                return rotatePdf(files);
//            case "unlock-pdf":
//                return unlockPdf(files);
//            case "protect-pdf":
//                return protectPdf(files);
//            case "add-watermark":
//                return addWatermark(files);
//            case "organize-pdf":
//                return organizePdf(files);
//            case "extract-pages":
//                return extractPages(files);
//            case "repair-pdf":
//                return repairPdf(files);
//            case "edit-pdf-meta":
//                return editPdfMetadata(files);
//            case "sign-pdf":
//                return signPdf(files);
            default:
                throw new IllegalArgumentException("Tool not supported: " + slug);
        }
    }


    // Dummy logic for merge (later replace with PDFBox)
    private String mergePdf(MultipartFile[] files) {
        return "PDFs successfully Merged!";
    }

    private String splitPdf(MultipartFile[] files) {
        return "PDF successfully Splited!";
    }

    private String compressPdf(MultipartFile[] files) {
        return " PDF successfully Compressed!";
    }
}
