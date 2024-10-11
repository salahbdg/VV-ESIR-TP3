# Mocks to the rescue

The classes `SSLSocket`, `TLSProtocol` and `TLSSocketFactory` are included in the `sockets` package of the [`tp3-ssl`](../code/tp3-ssl) project.

The test class `TLSSocketFactoryTest` tests `TLSSocketFactory` and manually builds stubs and mocks for SSLSocket objects.

Rewrite these tests with the help of Mockito.

The initial tests fail to completely test the `TLSSockeetFactory`. In fact, if we *entirely* remove the code inside the body of `prepareSocket` no test case fails.

Propose a solution to this problem in your new Mockito-based test cases.

# Explanation of the Code : 
Explanation of the code provided <a href="https://github.com/salahbdg/VV-ESIR-TP3/blob/dddd/code/tp3-ssl/src/test/java/fr/istic/vv/TLSSocketFactoryTestMocks.java">Here</a>
Lets me break down the key components and what they're testing.

The `TLSSocketFactory` handles SSL socket configuration, specifically focusing on protocol settings.

## Test Cases

### 1. Null Protocols Test (`testPrepareSocketWithNullProtocols`)
```java
@Test
public void testPrepareSocketWithNullProtocols() {
    TLSSocketFactory tlsSocketFactory = new TLSSocketFactory();
    SSLSocket mockSSLSocket = mock(SSLSocket.class);
    
    when(mockSSLSocket.getEnabledProtocols()).thenReturn(null);
    when(mockSSLSocket.getSupportedProtocols()).thenReturn(null);
    
    doAnswer(invocation -> {
        fail("setEnabledProtocols should not be called");
        return null;
    }).when(mockSSLSocket).setEnabledProtocols(any(String[].class));
    
    tlsSocketFactory.prepareSocket(mockSSLSocket);
}
```

#### What it tests:
- Verifies how the factory behaves when both supported and enabled protocols are null
- Ensures the factory doesn't attempt to set new protocols in this case
- Uses Mockito to simulate a socket with null protocols

### 2. Typical Protocol Configuration Test (`testTypicalProtocolConfiguration`)
```java
@Test
public void testTypicalProtocolConfiguration() {
    TLSSocketFactory tlsSocketFactory = new TLSSocketFactory();
    SSLSocket mockSSLSocket = mock(SSLSocket.class);
    
    String[] supportedProtocols = {"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"};
    String[] currentlyEnabledProtocols = {"SSLv3", "TLSv1"};
    String[] expectedEnabledProtocols = {"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"};
    
    when(mockSSLSocket.getEnabledProtocols())
        .thenReturn(shuffle(supportedProtocols));
    when(mockSSLSocket.getSupportedProtocols())
        .thenReturn(currentlyEnabledProtocols);
```

#### What it tests:
- Verifies the normal operation of the factory
- Checks if the factory correctly prioritizes protocols
- Ensures the factory sets the expected protocols in the correct order

## Testing Techniques Used

### 1. Mocking
- Uses Mockito to create mock `SSLSocket` objects
- Configures mock behavior using `when()` and `doAnswer()`

### 2. Verification
- Verifies method calls using Mockito's verification features
- Uses assertions to check expected outcomes

### 3. Random Testing
```java
private String[] shuffle(String[] inputArray) {
    List<String> protocolList = new ArrayList<>(Arrays.asList(inputArray));
    Collections.shuffle(protocolList);
    return protocolList.toArray(new String[0]);
}
```
- Randomizes protocol order to ensure the test isn't order-dependent

## Expected Behavior of TLSSocketFactory
Based on these tests, we can infer that the `TLSSocketFactory` class should:
1. Handle null protocols gracefully
2. Prioritize protocols in a specific order (TLSv1.2 > TLSv1.1 > TLSv1 > SSLv3)
3. Set enabled protocols based on what's supported by the socket
