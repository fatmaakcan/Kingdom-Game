
package krallikoyunu;

import java.util.Scanner;

public class Krallik {

    private double toplamAltin;
    private double toplamYemek;
    private int genelMemnuniyet;
    private int genelOrduSeviyesi;    //Krallık sınıfına ait alanlar(fields) tanımlanmıştır.
    private int guvenlikSeviyesi;
    private Sehir[] sehirListesi;
    private int turSayisi = 0; //Oyunun tur sayısını tutmak için turSayisi adlı bir özellik eklenmiştir.
    private Scanner input = new Scanner(System.in); //Scanner objesi birden fazla kez kullanılacağı için field olarak tanımlanmıştır.

    public void oyunuBaslat() {
        this.toplamAltin = 500; //Krallığın başlangıç altın miktarı belirlenmiştir.
        this.toplamYemek = 300; //Krallığın başlangıç yiyecek miktarı belirlenmiştir.
        this.sehirListesi = new Sehir[3]; //Şehir sınıfı tipinde 3 elemanlı bir dizi oluşturulmuştur.
        sehirListesi[0] = new TicaretSehri("Feniland", 8, 7, 10, 12, 15, 0.1); //Tarım,ticaret ve sanayi şehirlerinden birer örnek oluşturulup diziye atanmıştır.
        sehirListesi[1] = new TarimSehri("Neoland", 9, 6, 9, 10, 14, 0.1);
        sehirListesi[2] = new SanayiSehri("Modeland", 9, 6, 11, 11, 13, 0.1);
        for (int i = 1; i <= 10; i++) {  //Oyun for döngüsü ile 10 tur olacak şekilde ayarlanmıştır.

            turSayisi = i; //turSayisi değişkenine mevcut tur numarası atanmıştır.
            turuOyna(); //turuOyna() metodu çağrılır.

            if (oyunBittiMi()) { //Her tur sonunda oyunnun bitip bitmediği kontrol edilir. Eğer oyunBittiMi() true dönerse if bloğu çalışır ve oyun sonlanır.
                System.out.println("Oyun bitmistir!"); //Kullanıcıya oyunun bittiği bilgisi verilir.
                break; //break anahtar kelimesi ile döngü sonlandırılır.
            }
        }

    }

    public void turuOyna() {
        System.out.println("=== Orta Cag Kralligi Yonetim Oyunu ===");//Oyun adı ekrana yazdırılır.
        System.out.println();
        System.out.println("---------- " + turSayisi + ". YIL ----------"); //Kullanıcıya kaçıncı turda olduğu bilgisi verilir.
        System.out.printf("Kralligin toplam altin: %.2f%n", toplamAltin); //Krallığın toplam altın miktarı ekrana yazdırılır.
        System.out.printf("Kralligin toplam yiyecegi: %.2f%n", toplamYemek); //Krallığın toplam yiyecek miktarı yazdırılır.
        System.out.println("Kralligin ordu gucu: " + sehirlerinOrduSeviyesiToplami()); //Krallığın toplam ordu gücü ekrana yazdırılır.
        System.out.println("Krallik halkinin memnuniyeti: " + sehirlerinHalkMemnuniyetiToplami()); //Krallığın halk memnuniyeti ekrana yazdırılır.
        System.out.println("Kralligin guvenlik seviyesi: " + sehirlerinGuvenlikSeviyesiToplami()); //Krallığın güvenlik seviyesi ekrana yazdırılır.
        System.out.println();
        System.out.println("Kralliktaki Bolgeler: ");
        System.out.println();
        for (Sehir sehirListesi1 : sehirListesi) { //Şehirlerin özet durumunu ekrana yazdırmak için bir for-each döngüsü kullanılmıştır.
            System.out.println("Sehrin adi: " + sehirListesi1.getAd()); //İlgili şehrin adı ekrana yazdırılır.
            System.out.println("Uretim kapasitesi: " + sehirListesi1.getUretimKapasitesi());//İlgili şehrin üretim kapasitesi ekrana yazdırılır.
            System.out.printf("Vergi orani: %.2f %n", sehirListesi1.getVergiOrani()); //İlgili şehrin vergi oranı ekrana yazdırılır.
            System.out.println("-------------------------");

        }
        System.out.println();
        System.out.println("*****Menu*****"); //Kullanıcıya oyun menüsünün gösterileciği bilgisi konsola yazdırılır.
        int secim; //Kullanıcının yaptığı seçimi tutmak için bir değişken tanımlanmıştır.
        do { //Kullanıcıya gösterilecek menü do-while döngüsü ile oluşturulmuştur ve en az bir kez menü bölümünün görüntülenmesi garanti altına alınmıştır.
            //Kllanıcının seçebileceği seçenekler ekrana yazdırılmıştır.
            System.out.println("1 -> Sehirleri goruntule");
            System.out.println("2 -> Sehre yatirim yap");
            System.out.println("3 -> Vergi oranini ayarla");
            System.out.println("4 -> Turu bitir");
            System.out.print("Birini secin: ");
            secim = input.nextInt(); //Kullanıcıdan bir tam sayı girişi alınır.
            if (secim < 1 || secim > 4) { //Hatalı girişlerin önlenmesi için kontrol yapılır.
                System.out.println("Hatali giris yaptiniz!Lutfen 1 ile 4 arasi bir sayi giriniz!");//Eğer kullanıcı hatalı bir giriş yaparsa bu çıktı mesajıyla kullanıcıya bildirilir.
                secim = input.nextInt(); //Hatalı giriş sonucu kullanıcıdan tekrar bir input alınır. 
            }
            switch (secim) { //Menü işlemleri switch-case ile yapılır.
                case 1:
                    sehirleriGoruntule(); //Eğer kullancı 1'i girerse sehirleriGoruntule() metodu çalışır.
                    break; //break anahtar kelimesi ile diğer durumların çalışması önlenir.
                case 2:
                    sehireYatirimYap();//Kullanıcı 2 numarayı seçerse sehireYatirimYap() metodu çalışır.

                    break;
                case 3:
                    vergiyiAyarla(); //3 numara ile vergiyiAyarla() metodu çalışır.
                    break;
                case 4:
                    //4 numara ile döngü sonlanıp tur sonu hesaplama aşamasına geçilir.
                    break;
            }

        } while (secim != 4); //Kullanıcı 4 numarayı seçmediği sürece döngü çalışmaya devam eder.
        turSonuUretim(); //Kullanıcı turu bitirmek için 4 numarayı seçtiğinde bu satır çalışır ve turSonuUretim() metodu çağrılır.
        System.out.println();
    }

    public void sehirleriGoruntule() {
        for (Sehir sehirListesi1 : sehirListesi) {  //for-each döngüsü ile Krallik sınıfının oyunuBaslat() metodunda oluşturulan şehir dizisinin elemanları gezilir.
            sehirListesi1.bilgiGoster();    //Şehir listesindeki her bir eleman için Sehir sınıfındaki bilgiGoster() metodu çağrılır ve ilgili şehrin bilgileri ekrana yazdırılır.
            System.out.println();
        }

    }

    public void sehireYatirimYap() {
        for (int i = 0; i < sehirListesi.length; i++) { //for dönügüsü ile sehirListesi dizisinin elemanları gezilir.
            System.out.println((i + 1) + "." + sehirListesi[i].getAd() + " "); //İlgili şehrin adı ekrana yazdırılır. (i+1) kullanılmasının sebebi ise ekrana şehirleri 1'den başlayarak daha kullanıcı dostu bir şekilde listelemektir.
        }
        System.out.println("Bir sehir seciniz:"); //Kullanıcıdan bir şehir seçmesi istenir.
        int secim = input.nextInt(); //Kullanıcıdan input girişi alınır.
        if (secim >= 1 && secim <= sehirListesi.length) { //Olası bir hatalı giriş önlenir.

            System.out.println("Ne kadar altin yatiracaksiniz: "); //Kullanıcıya ne kadarlık bir yatırım yapılacağı sorulur.
            int altinMiktari = input.nextInt(); //Kullanıcıdan gireceği altın miktarı için input alınır.

            if (altinMiktari > 0 && altinMiktari < toplamAltin) { //Sadece pozitif ve krallığın altın miktarından az olan değerler kabul edilir.
                sehirListesi[secim - 1].yatirimYap(altinMiktari); //Kullanıcının seçtiği şehrin Sehir sınıfındaki yatirimYap() metodu çağrılır ve parametre olarak da kullanıcının girdiği altın miktarı gönderilir.
                this.toplamAltin = this.toplamAltin - altinMiktari; // Krallığın hazinesinden yatırım yapılan altın miktarı düşülür ve hazine güncellenir.
                System.out.println(sehirListesi[secim - 1].getAd() + " icin " + altinMiktari + " altin yatirildi."); //Kullanıcıya işlem ile ilgili mesaj verilir.
                System.out.println("Gelisim seviyesi: " + sehirListesi[secim - 1].getGelisimSeviyesi()); //Kullanıcıya yatırım yapılan şehrin gelisimSeviyesi ile ilgili geri bildirim verilir.
                System.out.println("Ordu seviyesi: " + sehirListesi[secim - 1].getOrduSeviyesi()); //Kullanıcıya yatırım yapılan şehrin orduSeviyesi ile ilgili geri bildirim verilir.
            } else { //Eğer ki kullanıcı geçersiz bir altın miktarı girerse else bloğu çalışır.
                System.out.println("Yeterli altin yok!"); //Kullanıcıya hatalı giriş ve yetersiz bakiye durumunda geri bildirim verilir.
            }

        } else { //Kullanıcı sehirListesi dizisinden bir eleman seçmek yerine geçersiz bir değer girerse bu else bloğu çalışır.
            System.out.println("Gecersiz bir secim yaptiniz!"); //Kullanıcıya geçersiz bir seçim yaptığına dair mesaj verilir.
        }
    }

    public void vergiyiAyarla() {
        for (int i = 0; i < sehirListesi.length; i++) { //for dönügüsü ile sehirListesi dizisinin elemanları gezilir.
            System.out.println((i + 1) + "." + sehirListesi[i].getAd() + " "); //İlgili şehrin adı ekrana yazdırılır. (i+1) kullanılmasının sebebi ise ekrana şehirleri 1'den başlayarak daha kullanıcı dostu bir şekilde listelemektir.
        }
        System.out.println("Bir sehir seciniz:"); //Kullanıcıdan bir şehir seçmesi istenir.
        int secim = input.nextInt(); //Kullanıcıdan input girişi alınır.
        if (secim >= 1 && secim <= sehirListesi.length) {  // Bu if bloğu ile olası bir hatalı giriş önlenir.
            System.out.println("Ne kadarlik bir vergi artisi yapacaksiniz: "); //Kullanıcıya ne kadarlık bir vergi artışı yapılacağı sorulur.
            double vergi = input.nextDouble(); //Kullanıcıdan gireceği vergi artışı için input alınır.
            if (sehirListesi[secim - 1].getVergiOrani() + vergi < 0.0 || sehirListesi[secim - 1].getVergiOrani() + vergi > 1.0) { //Bu if bloğu ile vergi oranının 0 ie 1 arasında kalması kontrol edilir.
                System.out.println("Vergi orani 0'dan kucuk veya 1'den buyuk olamaz!"); //Kullanıcının girdiği değer verginin negatif ya da 1'den büyük olmasına sebep olursa bu mesaj ekrana yazdırılır.
            } else {
                System.out.printf(sehirListesi[secim - 1].getAd() + " icin vergi orani: %.2f%n", sehirListesi[secim - 1].getVergiOrani()); //Öncelikle ekrana eski vergi oranı yazdırılır.
                sehirListesi[secim - 1].setVergiOrani(sehirListesi[secim - 1].getVergiOrani() + vergi); //Eski vergi oranı güncellenir.
                System.out.printf("->%.2f %n", sehirListesi[secim - 1].getVergiOrani()); //Yeni vergi oranı ekrana yazdırılır.
            }

        } else { //Kullanıcı geçersiz bir seçim yaparsa bu else bloğu çalışır.
            System.out.println("Gecersiz bir deger girdiniz!"); //Kullanıcıya geçersiz bir değer girdiğine dair uyarı mesajı verilir.
        }

    }

    public void turSonuUretim() {
        for (Sehir sehirListesi1 : sehirListesi) { //for dönügüsü ile sehirListesi dizisinin elemanları gezilir.
            sehirListesi1.turSonuUretimHesapla(this); //Dizideki her bir elemanın Sehir sınıfındaki turSonuUretimHesapla() metodu çağrılır.

        }

        yillikYiyecekTuketimi(); //Her tur sonunda bu metod çağrılır ve krallığın toplam yiyecek miktarından bir miktar düşülür.
        this.genelMemnuniyet = sehirlerinHalkMemnuniyetiToplami(); //Her tur sonunda şehirlerin bireysel memnuniyet değeri bu yardımcı metod ile toplanır ve krallığın genel memnuniyet değeri güncellenir.
        this.guvenlikSeviyesi = sehirlerinGuvenlikSeviyesiToplami(); //Her tur sonunda şehirlerin bireysel güvenlik seviyeleri bu yardımcı metod ile toplanır ve krallığın güvenlik seviyesi güncellenir.
        this.genelOrduSeviyesi = sehirlerinOrduSeviyesiToplami(); //Her tur sonunda şehirlerin bireysel ordu seviyeleri bu yardımcı metod ile toplanır ve krallığın genel ordu seviyesi güncellenir. 

        turOzetiYazdir(); //Her tur sonunda o turun özetini görebilmek için turOzetiYazdir() metodu çağrılır.

    }

    public void yillikYiyecekTuketimi() { //Bu metod her tur sonunda krallığın toplam yiyecek miktarından belirli bir miktar düşülmesi için yazılmıştır.
        if (toplamYemek - 30 < 0) { //Bu if bloğu negatif yemek ihtimaline karşı kontrol yapmaktadır.

            toplamYemek = 0;//Eğer ki yemek miktarı sıfırın altına düşerse yemek miktarı sıfıra eşitlenir.
        } else { //Bir sorun olmaması durumunda bu else bloğu çalışır.
            toplamYemek -= 30; //Ve toplam yemek miktarından 30 birim düşülür. 
        }
    }

    public void turOzetiYazdir() { //Bu metod her tur sonu, tur özetini yazdırması için yazılmıştır.
        System.out.println();
        System.out.println("--- " + turSayisi + " .YIL OZETI ---"); //Hangi tur olduğu ekrana yazdırılır.
        System.out.printf("Toplam altin: %.2f%n", this.toplamAltin); //Krallığın tur sonundaki toplam altını yazdırılır.
        System.out.printf("Toplam yiyecek: %.2f%n", this.toplamYemek); //Krallığın tur sonundaki toplam yiyecek miktarı yazdırılır.
        System.out.println("Guvenlik: " + this.guvenlikSeviyesi);//Krallığın tur sonundaki toplam güvenlik seviyesi ekrana yazdırılır.
        System.out.println("Memnuniyet: " + this.genelMemnuniyet);//Krallığın tur sonundaki genel memnuniyeti ekrana yazdırılır.
        System.out.println("Ordu gucu: " + this.genelOrduSeviyesi);//Krallığın tur sonundaki genel ordu seviyesi ekrana yazdırılır.
    }

    public boolean oyunBittiMi() { //Bu metod oyunun bitip bitmediğini konrol eder.
        return turSayisi == 10 || genelMemnuniyet < 10 || guvenlikSeviyesi < 10 || this.toplamAltin <= 0 || this.toplamYemek <= 0; //Bu şartlardan en az birinin gerçekleşmesi ile oyun sonlanır.
    }

    public int sehirlerinOrduSeviyesiToplami() { //Bu metod tüm şehirlerin ordu seviyelerini toplamak için yazılmıştır.
        int sum = 0; //Bir sum değişkeni oluşturulmuştur.
        for (Sehir sehirListesi1 : sehirListesi) { //for-each ile tüm dizi gezilir.
            sum += sehirListesi1.getOrduSeviyesi(); //Listedeki her bir şehrin ordu seviyesi toplanıp sum değişkenine atanır.

        }
        return sum; //return ile sum değeri döndürülür.
    }

    public int sehirlerinHalkMemnuniyetiToplami() { //Bu metod tüm şehirlerin halk memnuniyetlerini toplamak için yazılmıştır.
        int sum = 0; //Bir sum değişkeni oluşturulmuştur.
        for (Sehir sehirListesi1 : sehirListesi) { //for-each ile tüm dizi gezilir.
            sum += sehirListesi1.getHalkMemnuniyeti(); //Listedeki herbir şehrin halk memnuniyeti toplanıp sum değişkenine atanır.

        }
        return sum; //return ile sum değeri döndürülür.
    }

    public int sehirlerinGuvenlikSeviyesiToplami() { //Bu metod tüm şehirlerin güvenlik seviyelerini toplamak için yazılmıştır.
        int sum = 0; //Bir sum değişkeni oluşturulmuştur.
        for (Sehir sehirListesi1 : sehirListesi) { //for-each ile dizi gezilir.
            sum += sehirListesi1.getGuvenlikSeviyesi(); //Listedeki her bir şehrin güvenlik seviyesi toplanıp sum değişkenine atanır.

        }
        return sum; //return ile sum değeri döndürülür.
    }

    //Gerekli getter-setter metodları oluşturulmuştur.
    public double getToplamAltin() {
        return toplamAltin;
    }

    public void setToplamAltin(double toplamAltin) {
        this.toplamAltin = toplamAltin;
    }

    public double getToplamYemek() {
        return toplamYemek;
    }

    public void setToplamYemek(double toplamYemek) {
        this.toplamYemek = toplamYemek;
    }

}
