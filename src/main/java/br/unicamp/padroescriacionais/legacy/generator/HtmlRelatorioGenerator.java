package br.unicamp.padroescriacionais.legacy.generator;

import br.unicamp.padroescriacionais.legacy.domain.Relatorio;

public class HtmlRelatorioGenerator implements RelatorioGenerator {

    @Override
    public String gerar(Relatorio relatorio) {
        String conteudoHtml = escapeHtml(relatorio.getConteudo())
                .replace("\n", "<br/>\n");

        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\n");
        sb.append("<html lang=\"pt-BR\">\n");
        sb.append("<head>\n");
        sb.append("  <meta charset=\"UTF-8\"/>\n");
        sb.append("  <title>").append(escapeHtml(relatorio.getTitulo())).append("</title>\n");
        sb.append("  <style>\n");
        sb.append("    body { font-family: Arial, sans-serif; margin: 40px; }\n");
        sb.append("    h1   { color: #333; border-bottom: 2px solid #333; }\n");
        sb.append("    .meta { color: #666; font-size: 0.9em; margin-bottom: 20px; }\n");
        sb.append("    .conteudo { background: #f9f9f9; padding: 16px; border-left: 4px solid #333; }\n");
        sb.append("  </style>\n");
        sb.append("</head>\n");
        sb.append("<body>\n");
        sb.append("  <h1>").append(escapeHtml(relatorio.getTitulo())).append("</h1>\n");
        sb.append("  <div class=\"meta\">\n");
        sb.append("    <strong>Tipo:</strong> ").append(relatorio.getTipo().name()).append(" &nbsp;|&nbsp;\n");
        sb.append("    <strong>Gerado em:</strong> ").append(relatorio.getDataGeracao()).append("\n");
        sb.append("  </div>\n");
        sb.append("  <div class=\"conteudo\">\n");
        sb.append("    ").append(conteudoHtml).append("\n");
        sb.append("  </div>\n");
        sb.append("</body>\n");
        sb.append("</html>\n");
        return sb.toString();
    }

    private String escapeHtml(String valor) {
        if (valor == null) return "";
        return valor
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;");
    }
}