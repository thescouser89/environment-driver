package org.jboss.pnc.environmentdriver;

import java.util.Collections;
import java.util.HashMap;

import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;

import io.fabric8.kubernetes.client.NamespacedKubernetesClient;
import io.fabric8.kubernetes.client.server.mock.KubernetesCrudDispatcher;
import io.fabric8.kubernetes.client.server.mock.KubernetesMockServer;
import io.fabric8.mockwebserver.Context;
import io.fabric8.mockwebserver.MockWebServer;

/**
 * @author <a href="mailto:matejonnet@gmail.com">Matej Lazar</a>
 */
public class MockProducer {

    KubernetesMockServer kubernetesMockServer;

    MockProducer() {
        kubernetesMockServer = new KubernetesMockServer(
                new Context(),
                new MockWebServer(),
                new HashMap<>(),
                new KubernetesCrudDispatcher(Collections.emptyList()),
                false);
        kubernetesMockServer.init();
    }

    @Singleton
    @Produces
    public KubernetesMockServer getOpenShiftServer() {
        return kubernetesMockServer;
    }

    @Singleton
    @Produces
    public NamespacedKubernetesClient getOpenShiftClient() {
        return kubernetesMockServer.createClient();
    }
}
