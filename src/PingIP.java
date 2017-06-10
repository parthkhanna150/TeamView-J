public class PingIP {

   

    public static void main(String[] args) {
        int i = 0;
        while (i <= 255) {
            String ip = "172.16.1." + i;

            PingSingleIP iP = new PingSingleIP(ip);
            Thread t = new Thread(iP);
            t.start();
            i++;
//            runSystemCommand("ping " + ip);
//            i++;
        }

    }

}
