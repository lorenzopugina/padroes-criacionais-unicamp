package br.unicamp.padroescriacionais.legacy;

  import br.unicamp.padroescriacionais.legacy.domain.ConfiguracaoSistema;
  import br.unicamp.padroescriacionais.legacy.service.ConfiguracaoService;
  import org.junit.jupiter.api.BeforeEach;
  import org.junit.jupiter.api.Test;

  import java.lang.reflect.Field;

  import static org.junit.jupiter.api.Assertions.*;

  class ConfiguracaoSistemaTest {

      @BeforeEach
      void resetarSingleton() throws Exception {
          Field instancia = ConfiguracaoSistema.class.getDeclaredField("instancia");
          instancia.setAccessible(true);
          instancia.set(null, null);
      }

      @Test
      void deveRetornarInstanciaNaoNula() {
          assertNotNull(ConfiguracaoSistema.getInstance());
      }

      @Test
      void deveRetornarSempreAMesmaInstancia() {
          ConfiguracaoSistema a = ConfiguracaoSistema.getInstance();
          ConfiguracaoSistema b = ConfiguracaoSistema.getInstance();

          assertSame(a, b);
      }

      @Test
      void devePermitirAlteracaoDeAmbiente() {
          ConfiguracaoSistema config = ConfiguracaoSistema.getInstance();
          config.setAmbiente("PROD");

          assertEquals("PROD", config.getAmbiente());
      }

      @Test
      void devePermitirAlteracaoDeDebug() {
          ConfiguracaoSistema config = ConfiguracaoSistema.getInstance();
          config.setDebugAtivo(true);

          assertTrue(config.isDebugAtivo());
      }

      @Test
      void devePermitirAlteracaoDeDiretorio() {
          ConfiguracaoSistema config = ConfiguracaoSistema.getInstance();
          config.setDiretorioExportacao("/novo/diretorio");

          assertEquals("/novo/diretorio", config.getDiretorioExportacao());
      }

      @Test
      void alteracaoNaInstanciaDeveSerVisivelEmOutrasReferencias() {
          ConfiguracaoSistema config1 = ConfiguracaoSistema.getInstance();
          ConfiguracaoSistema config2 = ConfiguracaoSistema.getInstance();

          config1.setAmbiente("PROD");

          assertEquals("PROD", config2.getAmbiente());
      }

      @Test
      void configuracaoServiceDeveRetornarConfiguracaoNaoNula() {
          ConfiguracaoService service = new ConfiguracaoService();
          assertNotNull(service.getConfiguracao());
          assertFalse(service.getConfiguracao().getNomeEmpresa().isBlank());
      }
  }