import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    int num = 0;

    public String[] expand(String[] original) {
        String[] array = new String[original.length+1];
        for(int i = 0; i < original.length; i++) {
            array[i] = original[i];
        }
        return original = array;
    }

    public String handleRequest(URI url) {
        String[] list = new String[0];
        if (url.getPath().equals("/")) {
            return "Welcome!";
        }
        else if(url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                expand(list);
                list[list.length-1] = parameters[1];
            }
            return null;
        }
        else {
            return "404 Not Found!";
        }
    }
}

class NumberServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
