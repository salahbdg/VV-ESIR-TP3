package fr.istic.vv;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for TLSSocketFactory using Mockito framework
 * Tests the behavior of socket preparation with different protocol configurations
 */
public class TLSSocketFactoryTestMocks {

    /**
     * Test case for handling null protocols, it verifies that the factory handles the case when both supported and enabled protocols are null without setting new protocols
     */
    @Test
    public void testPrepareSocketWithNullProtocols() {
        // Create an instance of the class under test
        TLSSocketFactory tlsSocketFactory = new TLSSocketFactory();
        
        // Create a mock SSL socket
        SSLSocket mockSSLSocket = mock(SSLSocket.class);

        // Configure mock to return null for protocol methods
        when(mockSSLSocket.getEnabledProtocols()).thenReturn(null);
        when(mockSSLSocket.getSupportedProtocols()).thenReturn(null);

        // Configure mock to fail if setEnabledProtocols is called
        // This is necessary because the method is void and we need to verify it's not called
        doAnswer(invocation -> {
            fail("setEnabledProtocols should not be called when protocols are null");
            return null; // Required return for void method
        }).when(mockSSLSocket).setEnabledProtocols(any(String[].class));
        
        // Execute the method under test
        tlsSocketFactory.prepareSocket(mockSSLSocket);
    }
    
    /**
     * Test case for typical usage scenario. It verifies that the factory correctly prioritizes and sets protocols, when both supported and enabled protocols are available
     */
    @Test
    public void testTypicalProtocolConfiguration() {
        TLSSocketFactory tlsSocketFactory = new TLSSocketFactory();
        
        SSLSocket mockSSLSocket = mock(SSLSocket.class);
        
        // protocol arrays
        String[] supportedProtocols = {"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"};
        String[] currentlyEnabledProtocols = {"SSLv3", "TLSv1"};
        String[] expectedEnabledProtocols = {"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"};

        // Configure mock behavior for the protocol methods
        when(mockSSLSocket.getEnabledProtocols()).thenReturn(shuffle(supportedProtocols));
        when(mockSSLSocket.getSupportedProtocols()).thenReturn(currentlyEnabledProtocols);
        
        // Configure mock to verify the protocols being set
        doAnswer(invocation -> {
            String[] actualEnabledProtocols = invocation.getArgument(0);
            assertTrue(Arrays.equals(actualEnabledProtocols, expectedEnabledProtocols),
                    "Enabled protocols do not match expected protocols");
            return null; // Required return for void method
        }).when(mockSSLSocket).setEnabledProtocols(expectedEnabledProtocols);

        // execution of the method
        tlsSocketFactory.prepareSocket(mockSSLSocket);
    }
    
    /**
     * Utility method to randomize the order of protocols in an array. This ensures the test isn't dependent on the order of protocols
     * @param inputArray The array of strings to be shuffled
     * @return A new array with the same elements in random order
     */
    private String[] shuffle(String[] inputArray) {
        List<String> protocolList = new ArrayList<>(Arrays.asList(inputArray));
        Collections.shuffle(protocolList);
        return protocolList.toArray(new String[0]);
    }
}
