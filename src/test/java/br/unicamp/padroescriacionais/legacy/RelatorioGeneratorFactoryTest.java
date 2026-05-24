package br.unicamp.padroescriacionais.legacy;

import br.unicamp.padroescriacionais.legacy.domain.FormatoRelatorio;
import br.unicamp.padroescriacionais.legacy.factory.RelatorioGeneratorFactory;
import br.unicamp.padroescriacionais.legacy.generator.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RelatorioGeneratorFactoryTest {

    private RelatorioGeneratorFactory factory;

    @BeforeEach
    void setUp() {
        factory = new RelatorioGeneratorFactory();
    }

    @Test
    void deveCriarGeneratorPdfParaFormatoPdf() {
        assertInstanceOf(PdfRelatorioGenerator.class, factory.criar(FormatoRelatorio.PDF));
    }

    @Test
    void deveCriarGeneratorCsvParaFormatoCsv() {
        assertInstanceOf(CsvRelatorioGenerator.class, factory.criar(FormatoRelatorio.CSV));
    }

    @Test
    void deveCriarGeneratorJsonParaFormatoJson() {
        assertInstanceOf(JsonRelatorioGenerator.class, factory.criar(FormatoRelatorio.JSON));
    }

    @Test
    void deveCriarGeneratorXmlParaFormatoXml() {
        assertInstanceOf(XmlRelatorioGenerator.class, factory.criar(FormatoRelatorio.XML));
    }

    @Test
    void deveCriarGeneratorHtmlParaFormatoHtml() {
        assertInstanceOf(HtmlRelatorioGenerator.class, factory.criar(FormatoRelatorio.HTML));
    }

    @Test
    void todosFormatosDevemRetornarGeneratorNaoNulo() {
        for (FormatoRelatorio formato : FormatoRelatorio.values()) {
            assertNotNull(factory.criar(formato), "Generator nulo para formato: " + formato);
        }
    }

    @Test
    void formatosDiferentesDevemRetornarTiposDiferentes() {
        RelatorioGenerator pdf  = factory.criar(FormatoRelatorio.PDF);
        RelatorioGenerator csv  = factory.criar(FormatoRelatorio.CSV);
        RelatorioGenerator json = factory.criar(FormatoRelatorio.JSON);
        RelatorioGenerator xml  = factory.criar(FormatoRelatorio.XML);
        RelatorioGenerator html = factory.criar(FormatoRelatorio.HTML);

        assertNotEquals(pdf.getClass(),  csv.getClass());
        assertNotEquals(pdf.getClass(),  json.getClass());
        assertNotEquals(pdf.getClass(),  xml.getClass());
        assertNotEquals(pdf.getClass(),  html.getClass());
        assertNotEquals(csv.getClass(),  json.getClass());
        assertNotEquals(xml.getClass(),  html.getClass());
    }
}