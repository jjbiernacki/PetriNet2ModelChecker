package pkowalski.rtcp.runtime.coverability.output.dot;

import pkowalski.rtcp.runtime.coverability.NetState;
import pkowalski.rtcp.runtime.coverability.NetStateLink;
import pkowalski.rtcp.runtime.coverability.comparators.EqualityComparator;
import pkowalski.rtcp.runtime.coverability.comparators.ReachabilityComparator;
import pkowalski.rtcp.runtime.model.Place;
import pkowalski.rtcp.runtime.model.Token;
import pkowalski.utils.Func;
import pkowalski.utils.Utils;

import java.io.*;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: lordjim
 * Date: 23.06.11
 * Time: 10:11
 * To change this template use File | Settings | File Templates.
 */
public class GraphWriter {
    private EqualityComparator<NetState> stateEqualityComparator;
    private Writer outputWriter;
    private List<Place> placeOrder;

    public GraphWriter(String fileName, List<Place> placeOrder) throws FileNotFoundException {
        stateEqualityComparator = new ReachabilityComparator();
        outputWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
        this.placeOrder = placeOrder;
    }

    public void Write(List<NetStateLink> graph) throws IOException {
        final List<NetState> stateList = new ArrayList<NetState>();

        for(final NetStateLink link : graph){
            NetState state;

            try{
                state = Utils.SingleOrDefault(stateList, new Func<Boolean, NetState>() {
                    @Override
                    public Boolean Invoke(NetState netState) throws Exception {
                        return stateEqualityComparator.AreEqual(netState, link.getPredecessor());
                    }
                });

                if (state == null)
                    stateList.add(link.getPredecessor());
                else
                    link.setPredecessor(state);

                state = Utils.SingleOrDefault(stateList, new Func<Boolean, NetState>() {
                    @Override
                    public Boolean Invoke(NetState netState) throws Exception {
                        return stateEqualityComparator.AreEqual(netState, link.getSuccessor());
                    }
                });

                if (state == null)
                    stateList.add(link.getSuccessor());
                else
                    link.setSuccessor(state);

            }
            catch(Exception e){
                throw new RuntimeException(e);
            }
        }


        outputWriter.write("digraph G {\n");
        outputWriter.write("\trankdir=TB;\n");

        String nodeFormat = "\t%d [label = \"%s\\n%s\" shape=box];\n";
        String firstNodeFormat = "\t%d [label = \"%s\\n%s\\n%s\" shape=box];\n";
        String placeOrder = PlaceOrderToString();


        for(NetState state: stateList){
            int index = stateList.indexOf(state);
            String marking = MarkingToString(state.getMarking());
            String timestamps = TimestampToString(state.getTimestamps());
            if(index == 0)
                outputWriter.write(String.format(firstNodeFormat, index, placeOrder, marking, timestamps));
            else
                outputWriter.write(String.format(nodeFormat, index, marking, timestamps));
        }

        String linkFormat = "\t%d -> %d [label = \"(%s, %s)/%d\"];\n";
        for(NetStateLink link : graph){
            int predecessorIdx = stateList.indexOf(link.getPredecessor());
            int successorIdx = stateList.indexOf(link.getSuccessor());

            outputWriter.write(String.format(linkFormat, predecessorIdx, successorIdx, link.getTransition().getName(), link.getBinding(), link.getTime()));

        }

        outputWriter.write("}");

        outputWriter.flush();
        outputWriter.close();

    }

    private String PlaceOrderToString() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        boolean addComa = false;
        for(Place place : placeOrder){
            if (addComa){
                sb.append(", ");
            }
            else {
                addComa = true;
            }
            sb.append(place.getName());
        }
        sb.append(")");
        return sb.toString();
    }

    private String TimestampToString(Map<Place, Integer> timestamps){
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        boolean addComa = false;
        for(Place place:placeOrder){
            if(addComa)
                sb.append(", ");
            else
                addComa = true;

            sb.append(timestamps.get(place).toString());
        }
        sb.append(")");
        return sb.toString();
    }

    private String MarkingToString(Map<Place, List<Token>> marking){
        StringBuilder sb = new StringBuilder();

        sb.append("(");
        boolean addComa = false;
        for(Place place:placeOrder){
            if (addComa)
                sb.append(", ");
            else
                addComa = true;

            List<Token> tokens = marking.get(place);
            if (tokens.isEmpty())
                sb.append("-");
            else
                sb.append(TokensToString(tokens));
        }
        sb.append(")");

        return sb.toString();
    }

    private String TokensToString(List<Token> tokens){
        StringBuilder sb = new StringBuilder();
        TreeSet<String> uniqueTokens = new TreeSet<String>();
        for(Token token : tokens){
            uniqueTokens.add(token.toString());
        }
        boolean addPlus = false;
        for(String tokenString : uniqueTokens){
            if (addPlus)
                sb.append(" + ");
            else
                addPlus = true;
            int count = 0;
            for(Token token : tokens) {
                if (token.toString().equals(tokenString)) {
                    count++;
                }
            }
            if (count == 1) {
                sb.append(String.format("(%s)", tokenString));
            } else {
                sb.append(String.format("%d(%s)", count, tokenString));
            }
        }
        return  sb.toString();
    }
}
