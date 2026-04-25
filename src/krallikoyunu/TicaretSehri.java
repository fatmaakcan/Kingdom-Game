
package krallikoyunu;

public class TicaretSehri extends Sehir {

    public TicaretSehri(String ad, double uretimKapasitesi, int gelisimSeviyesi, int orduSeviyesi, int guvenlikSeviyesi, int halkMemnuniyeti, double vergiOrani) { //TicaretSehri'nin üst sınıfı (parent class) olan Sehir sınıfının özellikleri inheritance(kalıtım)ilkesi ile bu sınıfta tanımlanmıştır.
        super(ad, uretimKapasitesi, gelisimSeviyesi, orduSeviyesi, guvenlikSeviyesi, halkMemnuniyeti, vergiOrani);//Yapıcı metot (constructor) oluşturulmuştur.
    }

    @Override
    public void turSonuUretimHesapla(Krallik krallik) {
        double altinUretim = 0.8 * (super.getGelisimSeviyesi() + super.getUretimKapasitesi()); //Ticaret şehri altın odaklı bir üretim yaptığından dolayı ilgili şehrin gelişim seviyesi ve üretim kapasitesinin toplamının 0.8 katsayısı ile çarpılmasıyla altinUretimi hesaplanmaktadır.
        double yiyecekUretim = 0.2 * (super.getGelisimSeviyesi() + super.getUretimKapasitesi());//0.2 katsayısı ile çarpılmasıyla yiyecekUretimi hesaplanmaktadır.
        double altinVergisi = altinUretim * this.getVergiOrani(); //Yapılan altinUretimi, ilgili şehrin belirlenen vergiOrani ile çarpılarak altinVergisi hesaplanmaktadır.
        double yiyecekVergisi = yiyecekUretim * this.getVergiOrani(); //Yapılan yiyecekUretimi, ilgili şehrin belirlenen vergiOrani ile çarpılarak yiyecekVergisi hesaplanmaktadır.

        if (baskinGerceklesiyorMu()) { //Bu if bloğu baskın gerçekleşip gerçekleşmediğinin kontrolünü yapmaktadır.
            double altinKalan = altinUretim - 30; //Eğer ki baskın yapıldıysa, ilgili şehirde üretilen altinUretim'i baskından dolayı bir miktar kayba uğrar ve kalanı da altinKalan adlı değişkende saklanır.
            double yiyecekKalan = yiyecekUretim - 10;// Aynı şekilde, ilgili şehirde üretilen yiyecekUretim'i baskından dolayı bir miktar kayba uğrar ve kalanı da yiyecekKalan adlı bir değişkende saklanır.
            if (altinKalan <= 0 && yiyecekKalan <= 0) {//Bu if bloğu, baskından dolayı üretilen kaynakların her ikisinin de bitip bitmediğini veya negatife düşüp düşmediğinin kontrolünü sağlar.
                System.out.println(super.getAd() + " sehri icin baskindan dolayi kaynaklar tukendigi icin kralliga kaynak katkisi yok!");//Her iki kaynağın da tamamen tükenmesi durumunda kullanıcıya geri bildirim verilir.
            } else {//Baskından dolayı yiyecekUretim'i ve altınUretim'i sıfırlanmadıysa veya negatife düşmediyse bu else bloğu çalışmaktadır.

                if (altinKalan <= 0) {//Bu kısım, baskın sonrası altinKalan'ın sıfır veya negatife düşmesi durumunu kontrol etmektedir.
                    altinKalan = 0;//Negatife düşen altinKalan, hatalı hesaplamanın önüne geçmek için sıfıra eşitlenmektedir.
                    altinVergisi = 0;//Kaynak sıfırlandığı için krallığa herhangi bir aktarım olmamaktadır.
                    System.out.println("Baskindan dolayi " + super.getAd() + " sehrinde uretilen altin sifirlandigi icin kralliga katki saglanamadi.");//Kullanıcıya geri bildirim verilir.
                } else {
                    krallik.setToplamAltin(krallik.getToplamAltin() + (altinKalan * this.getVergiOrani()));//Eğer ki altinKalan negatif değilse bu satır çalışmaktadır ve krallığa baskından kalan altinKalan miktarı üzerinden hesaplanan kısım gönderilmektedir.
                }
                if (yiyecekKalan <= 0) {//Bu if bloğu, baskın sonrası yiyecekKalan'ın sıfır veya negatife düşmesi durumunu kontrol etmektedir.
                    yiyecekKalan = 0;//Negatife düşen yiyecekKalan, hatalı hesaplamanın önüne geçmek için sıfıra eşitlenmektedir.
                    yiyecekVergisi = 0;//Kaynak sıfırlandığı için krallığa herhangi bir aktarım olmamaktadır.
                    System.out.println("Baskindan dolayi " + super.getAd() + " sehrinde uretilen yiyecek sifirlandigi icin kralliga katki saglanamadi.");//Kullanıcıya geri bildirim verilir.
                } else {
                    krallik.setToplamYemek(krallik.getToplamYemek() + (yiyecekKalan * this.getVergiOrani()));//Eğer ki yiyecekKalan negatif değilse bu satır çalışmaktadır ve krallığa baskından kalan yiyecekKalan miktarı üzerinden hesaplanan kısım gönderilmektedir.
                }
            }
            if (this.getHalkMemnuniyeti() - 1 < 0) {//Baskın gerçekleşmesi durumunda ilgili şehrin memnuniyet seviyesi 1 azalmaktadır ve bu azalmanın negatif bir sayıya neden olup olmayacağı kontrol edilmektedir.
                System.out.println("Halk memnuniyeti sifirin altina dusemez!");//Kullanıcıya geri bildirim verilmektedir.
                this.setHalkMemnuniyeti(0);//halkMemnuniyeti'nin negatif bir değere düşmesini engellemek için minimum sınır olan 0'a sabitlenmektedir.
            } else {//Negatif olma söz konusu olmadığı durumda bu else bloğu çalışmaktadır.
                this.setHalkMemnuniyeti(this.getHalkMemnuniyeti() - 1);//Ve halk memnuniyeti 1 azaltılmaktadır.
            }
        } else {//Baskın gerçekleşmediyse bu else bloğu çalışmaktadır.

            krallik.setToplamAltin(krallik.getToplamAltin() + altinVergisi);//turSonuUretimHesapla() metotunun başında hesaplanan altinVergisi krallığın altınına eklenmektedir.
            krallik.setToplamYemek(krallik.getToplamYemek() + yiyecekVergisi);//turSonuUretimHesapla() metotunun başında hesaplanan yiyecekVergisi krallığın yemek miktarına eklenmektedir.
        }

        super.turSonuUretimHesapla(krallik);//TicaretSehri'nin parent class'ı olan Sehir sınıfındaki turSonuUretimHesapla() metodu çağırılmaktadır.Bu metot, şehir bazlı vergi oranına bağı olarak ilgili şehrin halk memnuniyetini güncellemektedir.
        if (altinUretim >= 50) {//altinUretimi'nin 50'ye eşit ya da fazla olması durumunda bu if bloğu çalışmaktadır.
            if (this.getHalkMemnuniyeti() + 1 > 1000) {//Halk memnuniyetinin 1 artması ile belirlenen maksimum seviyenin aşılması durumunda çalışmaktadır.
                System.out.println("Halk memnuniyeti maksimum seviyeye ulasmistir! Halk memnuniyeti:  " + this.getHalkMemnuniyeti());//Aşma durumunda kullanıcıya geri bildirim verilmektedir ve maksimum halk memnuniyeti bildirilmektedir.
            } else {//Belirlenen seviyeyi aşma durumu söz konusu olmadığında bu else bloğu çalışmaktadır.
                this.setHalkMemnuniyeti(this.getHalkMemnuniyeti() + 1);//İlgili şehrin halk memnuniyeti 1 artırılmaktadır.
            }
        }
    }
}
