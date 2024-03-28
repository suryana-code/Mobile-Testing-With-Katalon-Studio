import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.github.javafaker.Faker as Faker




//<<<MEMULAI APLIKSI
int maxRetries = 5
int retries = 0
'loop if start application fail'
while (retries < maxRetries) {
    try {
        Mobile.startExistingApplication('com.topindo.android', FailureHandling.STOP_ON_FAILURE)
        break
    }
    catch (Exception e) {
        retries++
        if (retries < maxRetries) {
            println('/n/n/n Gagal memulai aplikasi, mencoba kembali ke-' + retries)
        }
    } 
}

//<<<MASUK KE LIST MENU-MENU
Mobile.tap(findTestObject('icn_Homepage (navbar)'), 0, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('icn_Lainnya (menu)'), 0)

int maxAttempts = 5
int attempt = 1
while (attempt <= maxAttempts) {
    try {
        Mobile.tap(findTestObject('txt_Cari Produk Lainnya (searchbar Lainnya)'), 0)
        Mobile.clearText(findTestObject('txt_Cari Produk Lainnya (searchbar Lainnya)'), 0)
        Mobile.delay(3)
        '<<< value searching'
        Mobile.setText(findTestObject('txt_Cari Produk Lainnya (searchbar Lainnya)'), 'paket data', 0)
        Mobile.delay(5)
        Mobile.pressBack()
        Mobile.waitForElementPresent(findTestObject('Lay_(hasil pencarian)'), 60)
        if (Mobile.verifyElementVisible(findTestObject('icn_ICONNET (menu)'), 10, FailureHandling.OPTIONAL)) {
            attempt = 1
            continue
        } else {
            Mobile.tap(findTestObject('Lay_(hasil pencarian)'), 0)
            break
        }
    }
    catch (Exception e) {
    } 
    attempt++
}




//<<<MELAKUKAN PENGUJIAN FUNGSI PADA HALAMAN PAKET DATA
Mobile.waitForElementPresent(findTestObject('Transaksi Pulsa/Produk Paket Data/txt_Pilih Produk'), 60)

Mobile.verifyElementText(findTestObject('Transaksi Pulsa/Produk Paket Data/txt_Paket Data'), 
    'Paket Data')

Mobile.verifyElementVisible(findTestObject('Transaksi Pulsa/img_banner'), 0)

Mobile.verifyElementVisible(findTestObject('Transaksi Pulsa/txt_Nomor Telepon'), 0)

Mobile.verifyElementVisible(findTestObject('Transaksi Pulsa/icn_Contact'), 0)

Mobile.verifyElementText(findTestObject('Transaksi Pulsa/txt_Pilih Produk'), 'Pilih Produk')

Mobile.scrollToText('xl', FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Transaksi Pulsa/Lay_list produk (list produk pertama Pulsa)'), 0)

Mobile.scrollToText('Nomor Telepon', FailureHandling.OPTIONAL)

Mobile.verifyElementText(findTestObject('Transaksi Pulsa/validate_Minimal Nomor Telepon 6 karakter'), 
    'Nomor Telepon harus diisi')

Mobile.tap(findTestObject('Transaksi Pulsa/icn_Contact'), 0)

Mobile.pressBack()

Mobile.tap(findTestObject('Transaksi Pulsa/fld_Masukan No Telepon'), 0)

//generate nomor acak
Faker faker = new Faker()
String generateFaker = faker.number().digits(3)
phoneNum_result = (('0877' + generateFaker) + '0000000')

Mobile.setText(findTestObject('Transaksi Pulsa/fld_Masukan No Telepon'), '123456789', 0)

Mobile.hideKeyboard()

Mobile.verifyElementNotVisible(findTestObject('Transaksi Pulsa/Lay_list produk (list produk pertama Pulsa)'), 
    5, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Transaksi Pulsa/fld_Masukan No Telepon'), 0)

Mobile.clearText(findTestObject('Transaksi Pulsa/fld_Masukan No Telepon'), 0)

Mobile.setText(findTestObject('Transaksi Pulsa/fld_Masukan No Telepon'), phoneNum_result, 0)

Mobile.hideKeyboard()

Mobile.waitForElementPresent(findTestObject('Transaksi Pulsa/Produk Paket Data/txt_XL (product)'), 
    60, FailureHandling.STOP_ON_FAILURE)

valuenotlpn = Mobile.getText(findTestObject('Transaksi Pulsa/fld_Masukan No Telepon'), 0)

Mobile.tap(findTestObject('Transaksi Pulsa/Produk Paket Data/txt_XL (product)'), 0)




//<<<MEMILIH NOMINAL TRANSAKSI PAKET DATA
Mobile.waitForElementPresent(findTestObject('Transaksi Pulsa/Produk Paket Data/txt_Pilih Nominal'), 60)

String valuenotlpn_PricePage = Mobile.getText(findTestObject('Transaksi Pulsa/Nominal Transaksi/noid_089xxx (disable field)'), 0)

Mobile.verifyEqual(valuenotlpn_PricePage, valuenotlpn)

price_selected = Mobile.getText(findTestObject('Transaksi Pulsa/Produk Paket Data/Pilih Nominal/nml_Rp1.xxx (price)'), 0)

Mobile.tap(findTestObject('Transaksi Pulsa/Produk Paket Data/Pilih Nominal/nml_Rp1.xxx (price)'), 0)




//<<<MELAKUKAN VERIFIKASI PADA HALAMAN KONFIRMASI PEMBAYARAN TRANSAKSI
Mobile.waitForElementPresent(findTestObject('Transaksi Pulsa/Nominal Transaksi/Konfirmasi Pembayaran/txt_Konfirmasi Pembayaran (title header)'), 60)

Mobile.verifyElementText(findTestObject('Transaksi Pulsa/Nominal Transaksi/Konfirmasi Pembayaran/txt_Konfirmasi Pembayaran (title header)'), 
    'Konfirmasi Pembayaran')

Mobile.verifyElementText(findTestObject('Transaksi Pulsa/Nominal Transaksi/Konfirmasi Pembayaran/txt_Nomor Telepon'), 
    'Nomor Telepon')

valuenotlpn_confirm = Mobile.getText(findTestObject('Transaksi Pulsa/Nominal Transaksi/Konfirmasi Pembayaran/noid_089xxx'), 0)

Mobile.verifyEqual(valuenotlpn_confirm, valuenotlpn)

Mobile.verifyElementText(findTestObject('Transaksi Pulsa/Nominal Transaksi/Konfirmasi Pembayaran/txt_Kode Produk'), 
    'Kode Produk')

Mobile.verifyElementText(findTestObject('Transaksi Pulsa/Nominal Transaksi/Konfirmasi Pembayaran/txt_Detail Pembayaran'), 
    'Detail Pembayaran')

Mobile.verifyElementText(findTestObject('Transaksi Pulsa/Nominal Transaksi/Konfirmasi Pembayaran/txt_Harga'), 
    'Harga')

price_confirm = Mobile.getText(findTestObject('Transaksi Pulsa/Nominal Transaksi/Konfirmasi Pembayaran/nml_Rp1.xxx (price)'), 0)

Mobile.verifyEqual(price_confirm, price_selected)

Mobile.verifyElementText(findTestObject('Transaksi Pulsa/Nominal Transaksi/Konfirmasi Pembayaran/txt_Total Pembayaran (payment page)'), 
    'Total Pembayaran', FailureHandling.STOP_ON_FAILURE)

totalPembayaran = Mobile.getText(findTestObject('Transaksi Pulsa/Nominal Transaksi/Konfirmasi Pembayaran/nml_Rp1.xxx (total payment)'), 0)

Mobile.verifyEqual(totalPembayaran, price_selected)

Mobile.tap(findTestObject('Transaksi Pulsa/Nominal Transaksi/Konfirmasi Pembayaran/btn_Bayar'), 0)




//<<<MELAKUKAN VERIFIKASI PADA HALAMAN INPUT PIN
Mobile.verifyElementVisible(findTestObject('Transaksi Pulsa/Nominal Transaksi/Konfirmasi Pembayaran/PIN Topindo/pge_Input PIN'), 0)

Mobile.verifyElementText(findTestObject('Transaksi Pulsa/Nominal Transaksi/Konfirmasi Pembayaran/PIN Topindo/txt_Masukkan PIN'), 
    'Masukkan PIN')

Mobile.verifyElementText(findTestObject('Transaksi Pulsa/Nominal Transaksi/Konfirmasi Pembayaran/PIN Topindo/txt_Masukkan PIN Saat Ini'), 
    'Masukkan PIN Saat Ini')

Mobile.verifyElementText(findTestObject('Transaksi Pulsa/Nominal Transaksi/Konfirmasi Pembayaran/PIN Topindo/txt_Masukkan PIN Anda'), 
    'Masukkan PIN Anda')

Mobile.verifyElementText(findTestObject('Transaksi Pulsa/Nominal Transaksi/Konfirmasi Pembayaran/PIN Topindo/txtLupa dengan PIN Anda'), 
    'Lupa dengan PIN Anda?')

Mobile.verifyElementText(findTestObject('Transaksi Pulsa/Nominal Transaksi/Konfirmasi Pembayaran/PIN Topindo/btn_Atur Ulang'), 
    'Atur Ulang')

Mobile.verifyElementVisible(findTestObject('Transaksi Pulsa/Nominal Transaksi/Konfirmasi Pembayaran/PIN Topindo/btn_Lanjutkan'), 0)

Mobile.tap(findTestObject('Transaksi Pulsa/Nominal Transaksi/Konfirmasi Pembayaran/PIN Topindo/txt_Masukkan PIN Anda'), 0)

Mobile.setText(findTestObject('Transaksi Pulsa/Nominal Transaksi/Konfirmasi Pembayaran/PIN Topindo/txt_Masukkan PIN Anda'), 
    '123456', 0)

Mobile.verifyElementText(findTestObject('Transaksi Pulsa/Nominal Transaksi/Konfirmasi Pembayaran/PIN Topindo/validate_PIN yang kamu masukkan salah'), 
    'PIN yang kamu masukkan salah')

Mobile.tap(findTestObject('Transaksi Pulsa/Nominal Transaksi/Konfirmasi Pembayaran/PIN Topindo/txt_Masukkan PIN Anda'), 0)

Mobile.clearText(findTestObject('Transaksi Pulsa/Nominal Transaksi/Konfirmasi Pembayaran/PIN Topindo/txt_Masukkan PIN Anda'), 0)

Mobile.setText(findTestObject('Transaksi Pulsa/Nominal Transaksi/Konfirmasi Pembayaran/PIN Topindo/txt_Masukkan PIN Anda'), 
    '111111', 0)

Mobile.waitForElementPresent(findTestObject('Transaksi Pulsa/Nominal Transaksi/Konfirmasi Pembayaran/PIN Topindo/bottomsheet double trx/btn_Ya'), 
    15, FailureHandling.OPTIONAL)

'transaksi yang sama'
if (Mobile.verifyElementVisible(findTestObject('Transaksi Pulsa/Nominal Transaksi/Konfirmasi Pembayaran/PIN Topindo/bottomsheet double trx/android.widget.TextView - Transaksi Yang Sama'), 
    3, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('Transaksi Pulsa/Nominal Transaksi/Konfirmasi Pembayaran/PIN Topindo/bottomsheet double trx/btn_Ya'), 0)

    Mobile.tap(findTestObject('Transaksi Pulsa/Nominal Transaksi/Konfirmasi Pembayaran/PIN Topindo/bottomsheet double trx/btn_Ya'), 
        0, FailureHandling.OPTIONAL)
}

Mobile.waitForElementPresent(findTestObject('Transaksi Pulsa/Nominal Transaksi/Konfirmasi Pembayaran/PIN Topindo/Halaman Berhasil/btn_Kembali Ke Home'), 60)

Mobile.verifyElementVisible(findTestObject('Transaksi Pulsa/Nominal Transaksi/Konfirmasi Pembayaran/PIN Topindo/Halaman Berhasil/pge_Transaksi Berhasil'), 0)

Mobile.verifyElementVisible(findTestObject('Transaksi Pulsa/Nominal Transaksi/Konfirmasi Pembayaran/PIN Topindo/Halaman Berhasil/img_Transaksi Berhasil'), 0)

Mobile.verifyElementText(findTestObject('Transaksi Pulsa/Nominal Transaksi/Konfirmasi Pembayaran/PIN Topindo/Halaman Berhasil/txt_Pembayaran Berhasil'), 
    'Pembayaran Berhasil')

Mobile.verifyElementText(findTestObject('Transaksi Pulsa/Nominal Transaksi/Konfirmasi Pembayaran/PIN Topindo/Halaman Berhasil/android.widget.TextView - Transaksi Sedang diproses'), 
    'Transaksi Sedang diproses')

Mobile.verifyElementText(findTestObject('Transaksi Pulsa/Nominal Transaksi/Konfirmasi Pembayaran/PIN Topindo/Halaman Berhasil/btn_Lihat Halaman Riwayat'), 
    'Lihat Halaman Riwayat')

Mobile.verifyElementText(findTestObject('Transaksi Pulsa/Nominal Transaksi/Konfirmasi Pembayaran/PIN Topindo/Halaman Berhasil/btn_Kembali Ke Home'), 
    'Kembali Ke Home')

Mobile.tap(findTestObject('Transaksi Pulsa/Nominal Transaksi/Konfirmasi Pembayaran/PIN Topindo/Halaman Berhasil/btn_Lihat Halaman Riwayat'), 0)

Mobile.verifyElementVisible(findTestObject('pge_Riwayat'), 0)




//<<<MELAKUKAN VERIFIKASI PADA HALAMAN RIWAYAT
Mobile.waitForElementPresent(findTestObject('noid_089xxx (Riwayat)'), 60, FailureHandling.OPTIONAL)

String no_transaction = Mobile.getText(findTestObject('noid_089xxx (Riwayat)'), 0)

'ID/no yang ditransaksikan'
Mobile.verifyEqual(no_transaction, valuenotlpn)

String pricetrxinRiwayat = Mobile.getText(findTestObject('nml_Rp1.xxx (nominal trx Riwayat)'), 0)

Mobile.verifyEqual(pricetrxinRiwayat, price_selected)

