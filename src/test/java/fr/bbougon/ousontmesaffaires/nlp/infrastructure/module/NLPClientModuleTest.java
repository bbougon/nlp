package fr.bbougon.ousontmesaffaires.nlp.infrastructure.module;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.testing.fieldbinder.BoundFieldModule;
import fr.bbougon.ousontmesaffaires.nlp.domain.NLPSettings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NLPClientModuleTest {

    @Inject
    private NLPSettings nlpSettings;

    @Test
    @DisplayName("it provides nlpSettings")
    public void canCreateModule() {
        NLPClientModule mongolinkModule = new NLPClientModule("version", "username", "password");
        Injector injector = Guice.createInjector(mongolinkModule, BoundFieldModule.of(this));
        injector.injectMembers(this);

        assertThat(nlpSettings).isNotNull();
        assertThat(nlpSettings.getVersion()).isEqualTo("version");
        assertThat(nlpSettings.getUsername()).isEqualTo("username");
        assertThat(nlpSettings.getPassword()).isEqualTo("password");
    }
}