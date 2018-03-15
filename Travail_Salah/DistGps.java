public class DistGps {

        public Double D;

        public Double  Distance (Double lat_a_degre, Double lon_a_degre, Double lat_b_degre, Double lon_b_degre){

            int R = 6378000 ;//Rayon de la terre en mètre
            double  lat_a = (Math.PI *lat_a_degre)/180;
            double lon_a = (Math.PI *lon_a_degre)/180;
            double lat_b = (Math.PI *lat_b_degre)/180;
            double lon_b = (Math.PI *lon_b_degre)/180;

            D = R * (Math.PI / 2 - Math.asin(Math.sin(lat_b) * Math.sin(lat_a) +
                           Math.cos(lon_b - lon_a) * Math.cos(lat_b) * Math.cos(lat_a)));
            return D;
    }
}