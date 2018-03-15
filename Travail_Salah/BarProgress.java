class BarProgress implements Runnable{
    Thread t;
    public void run(){
        Station_Sol.Demarrer.setEnabled(false);
        for( Double val = 0.0; val <= Station_Sol.distance.D+1 ; val+= Station_Sol.V){
            int d = val.intValue();
           Station_Sol.bar.setValue(d);
            try {
                t.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Station_Sol.Demarrer.setEnabled(true);
    }
}