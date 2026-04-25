//FATMA AKCAN
//2421021008
package krallikoyunu;

public abstract class Sehir {

    private String ad;
    private double uretimKapasitesi;
    private int gelisimSeviyesi;
    private int orduSeviyesi;
    private int guvenlikSeviyesi;           //Sehir sınıfına ait veri alanları (fields) tanımlanmıştır.
    private int halkMemnuniyeti;
    private double baskinIhtimali; //İlgili şehir için olası baskın ihtimalini tutmak için Sehir sınıfının bir field'ı olarak tanımlanmıştır.
    private double vergiOrani; //Vergi, şehir bazlı olarak ayarlandığı için vergiOrani, Sehir sınıfının bir field'ı olarak tanımlanmıştır.           

    public Sehir(String ad, double uretimKapasitesi, int gelisimSeviyesi, int orduSeviyesi, int guvenlikSeviyesi, int halkMemnuniyeti, double vergiOrani) {//Yapıcı metod (constructor) oluşturulmuştur.
        this.ad = ad;
        this.uretimKapasitesi = uretimKapasitesi;
        this.gelisimSeviyesi = gelisimSeviyesi;
        this.orduSeviyesi = orduSeviyesi;
        this.guvenlikSeviyesi = guvenlikSeviyesi;
        this.halkMemnuniyeti = halkMemnuniyeti;
        this.vergiOrani = vergiOrani;
    }

    public void yatirimYap(int altinMiktari) {
        int artis = altinMiktari / 100; //Her yatırım yapılan 100 altında artis değişkeni 1 artacak şekilde tanımlanmıştır.
        if (this.gelisimSeviyesi + artis > 1000) { //İlgili şehrin gelisimSeviyesi,o şehre yatırım yapılan altinMiktari ile artmaktadır. Bu if bloğunda, gelisimSeviyesi'nin artis ile belirlenen maksimum seviyeyi aşma durumu kontrol edilmektedir.
            System.out.println("Maksimum gelisim seviyesine ulasilmistir. Gelisim seviyesi: " + this.gelisimSeviyesi);//Kullanıcıya geri bildirim verilmektedir ve konsola maksimum seviyede olan gelisimSeviyesi yazdırılmaktadır.
        } else {//Belirlenen maksimum seviyeyi aşma söz konusu olmadığında bu else bloğu çalışmaktadır.
            this.gelisimSeviyesi += artis; //Aşma olmadığında gelisimSeviyesi hesaplanan artis miktarı ile güncellenmektedir.
        }
        if (this.orduSeviyesi + artis > 1000) {//İlgili şehrin orduSeviyesi,o şehre yatırım yapılan altinMiktari ile artmaktadır. Bu if bloğunda, orduSeviyesi'nin artis ile belirlenen maksimum seviyeyi aşma durumu kontrol edilmektedir.

            System.out.println("Maksimum ordu seviyesine ulasilmistir. Ordu seviyesi: " + this.orduSeviyesi);//Kullanıcıya geri bildirim verilmektedir ve konsola maksimum seviyede olan orduSeviyesi yazdırılmaktadır.
        } else {//Belirlenen maksimum seviyeyi aşma söz konusu olmadığında bu else bloğu çalışmaktadır.
            this.orduSeviyesi += artis;//Aşma olmadığında orduSeviyesi hesaplanan artis miktarı ile güncellenmektedir.
        }

    }

    public boolean baskinGerceklesiyorMu() {
        this.baskinIhtimali = 50 - (orduSeviyesi + guvenlikSeviyesi);//baskinIhtimali field'ı, belirlenen miktardan ilgili şehrin orduSeviyesi ve guvenlikSeviyesi'nin toplamının çıkartılmasıyla hesaplanmaktadır.
        if (baskinIhtimali <= 0) { //baskinIhtimali'nin sıfır veya negatif çıkması durumunda bu if bloğu çalışmaktadır.
            System.out.println(this.ad + " sehri baskin yapilamayacak derece guvenlidir!");//Kullanıcıya o şehirde baskın olmayacağının geri bildirimi verilmektedir.
            return false;//Metot false döndürür ve metottan çıkılır.
        } else {//baskinIhtimali'nin pozitif bir sayı olması durumunda bu else bloğu çalışmaktadır.
            int random = (int) (Math.random() * 100); //baskinIhtimali ile karşılaştırılacak 0-99 arasında rastgele bir sayı üretilmektedir ve random adlı değişkende saklanmaktadır.
            if (baskinIhtimali > random) {//baskinIhtimali, üretilen random sayıdan büyük ise bu blok çalışmaktadır.
                System.out.println("!!DIKKAT!! " + this.ad + " ICIN BASKIN VAR!!");//Baskın durumunda kullanıcıya geri bildirim verilir.
                if (this.guvenlikSeviyesi - 1 < 0) {//guvenlikSeviyesi, baskın yapılmasına bağlıdır ve baskın yapılırsa 1 azalmaktadır.Bu azalışın negatif bir sayıya sebep olması durumunda bu if bloğu çalışmaktadır.
                    System.out.println("Guvenlik seviyesi negatif bir sayi olamaz!");//Kullanıcıya geri bildirim verilir.
                    this.guvenlikSeviyesi = 0;//guvenlikSeviyesi'nin negatif bir değere düşmesini engellemek için guvenlikSeviyesi minimum sınır olan 0'a sabitlenmektedir.
                    return true;//Metot true döndürür ve metottan çıkılır.
                } else {//Negatife düşme söz konusu olmadığında bu blok çalışmaktadır.
                    this.guvenlikSeviyesi -= 1;//guvenlikSeviyesi,baskından dolayı 1 azaltılmaktadır.
                    return true;//Metot true döndürür ve metottan çıkılır.
                }
            } else {//baskinIhtimali'nin üretilen random sayıdan küçük olması durumunda bu blok çalışmaktadır.
                System.out.println(this.ad + " sehri icin baskin yok :)");//Kullanıcıya baskın olmayacağına dair geri bildirim verilir.
                return false;//Metot false döndürür ve metottan çıkılır.
            }

        }

    }

    public void turSonuUretimHesapla(Krallik krallik) {//Bu metotta tüm sınıflarda ortak olan vergiden kaynaklı meydana gelen halk memnuniyeti kontrol edilmiştir.Böylelikle alt sınıflardaki olası kod tekrarı önlenmiştir.
        if (this.getVergiOrani() >= 0.4) { //Bu blok vergi miktarından dolayı ortaya çıkan memnuniyet durumunu kontrol etmektedir.Eğer vergi 0.40'a eşit veya büyükse bu blok çalışmaktadır.
            if (this.getHalkMemnuniyeti() - 1 < 0) {//Memnuniyetin negatif olma durumu kontrol edilmektedir.
                System.out.println("Halk memnuniyeti 0'ın altina dusemez!");//Kullanıcıya geri bildirim verilir.
                this.setHalkMemnuniyeti(0);//halkMemnuniyeti'nin negatif bir değere düşmesini engellemek için halkMemnuniyeti minimum sınır(0)'a sabitlenmektedir.
            } else {//Negatif olma durumu söz konusu olmadığında çalışmaktadır.
                this.setHalkMemnuniyeti(this.getHalkMemnuniyeti() - 1);//Vergi yüksekliğinden dolayı ilgili şehir halkının memnuniyeti 1 azaltılmaktadır.
            }

        } else {//Verginin 0.40'tan az olması durumunda çalışmaktadır.
            if (this.getHalkMemnuniyeti() + 1 > 1000) {//Halk memnuniyetinin 1 artması ile belirlenen maksimum seviyeyi aşma durumunda çalışmaktadır.
                System.out.println("Halk memnuniyeti maksimum seviyeye ulasmistir! Halk memnuniyeti:  " + this.getHalkMemnuniyeti());//Kullanıcıya geri bildirim ve maksimum halk memnuniyeti bildirilmektedir.
            } else {//Maksimum durumu aşma söz konusu olmadığında bu blok çalışmaktadır.
                this.setHalkMemnuniyeti(this.getHalkMemnuniyeti() + 1);//İlgili şehrin halk memnuniyeti 1 artırılmaktadır.
            }

        }
    }

    public void bilgiGoster() { //İlgili şehrin bilgileri ekrana yazdırılır.
        System.out.println("Sehrin adi:" + this.ad); //İlgili şehrin adı ekrana yazdırılır.
        System.out.println("Turu: " + Sehir.this.getClass().getSimpleName()); //İlgili şehrin türü ekrana yazdırılır.
        System.out.println("Sehrin gelisim seviyesi: " + this.gelisimSeviyesi); //İlgili şehrin gelisimSeviyesi ekrana yazdırılır.
        System.out.println("Sehrin uretim kapasitesi: " + this.uretimKapasitesi); //İlgili şehrin uretimKapasitesi ekrana yazdırılır.
        System.out.println("Sehrin ordu seviyesi: " + this.orduSeviyesi); //İlgili şehrin orduSeviyesi ekrana yazdırılır.
        System.out.println("Sehrin guvenlik seviyesi: " + this.guvenlikSeviyesi); //İlgili şehrin guvenlikSeviyesi ekrana yazdırılır.
        System.out.println("Sehir halkinin memnuniyet seviyesi: " + this.halkMemnuniyeti); //İlgili şehrin halkMemnuniyeti ekrana yazdırılır.

    }

    //Gerekli getter-setter metotları oluşturulmuştur.
    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public double getUretimKapasitesi() {

        return uretimKapasitesi;
    }

    public void setUretimKapasitesi(double uretimKapasitesi) {
        this.uretimKapasitesi = uretimKapasitesi;
    }

    public int getGelisimSeviyesi() {
        return gelisimSeviyesi;
    }

    public void setGelisimSeviyesi(int gelisimSeviyesi) {
        this.gelisimSeviyesi = gelisimSeviyesi;
    }

    public int getOrduSeviyesi() {
        return orduSeviyesi;
    }

    public void setOrduSeviyesi(int orduSeviyesi) {
        this.orduSeviyesi = orduSeviyesi;
    }

    public int getGuvenlikSeviyesi() {
        return guvenlikSeviyesi;
    }

    public void setGuvenlikSeviyesi(int guvenlikSeviyesi) {
        this.guvenlikSeviyesi = guvenlikSeviyesi;
    }

    public int getHalkMemnuniyeti() {
        return halkMemnuniyeti;
    }

    public void setHalkMemnuniyeti(int halkMemnuniyeti) {
        this.halkMemnuniyeti = halkMemnuniyeti;
    }

    public double getVergiOrani() {
        return vergiOrani;
    }

    public void setVergiOrani(double vergiOrani) {
        this.vergiOrani = vergiOrani;
    }

}
