package pkowalski.rtcp.xml;

/**
 * Created by IntelliJ IDEA.
 * User:    Przemysław Kowalski
 * WEAIiE, AGH Kraków
 * pkowalski47@gmail.com
 * Project: RTCPnet SimGen
 * Date:    2010-05-30
 * Time:    15:34:56
 */
class SocketPortPair {


    public SocketPortPair(){
        // Add your code here:
        super();

    }


    private String _socketName;

    public String getSocketName() {
        return _socketName;
    }

    public void setSocketName(String value) {
        _socketName = value;
    }

    private String _portName;

    public String getPortName() {
        return _portName;
    }

    public void setPortName(String value) {
        _portName = value;
    }
}
