package com.telerik.extension_repository.repositories;

import com.sun.xml.internal.ws.protocol.soap.VersionMismatchException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;


@RunWith(MockitoJUnitRunner.class)
//@DataJpaTest
@ActiveProfiles("test")
public class PropertiesRepositoryTest {

    private static final int numOfThreads = 10;

    @Mock
    private PropertiesRepositoryImpl propertiesRepository;

    @Test(expected = VersionMismatchException.class)
    public void Simultaneous_Update_Of_UpdateInterval_Should_Throw_VersionMismatchException() {
        //ARRANGE
        Thread[] threads = new Thread[numOfThreads];
        for (int i = 0; i < numOfThreads; i++) {
            threads[i] = new Thread(() ->
                    propertiesRepository.updateInterval((long) (Math.random() * 10)));
        // ACT
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }
}