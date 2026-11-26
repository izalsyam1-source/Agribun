package com.example.data.source.content

import com.example.model.CaseStudy
import com.example.model.MatchingPair
import com.example.model.Question
import com.example.model.QuestionType
import com.example.model.TP

object CaseStudiesContent {
    val caseStudies: List<CaseStudy> = listOf(
        CaseStudy(
            id = "cs-1",
            unitId = "unit-1",
            tp = TP.TP1,
            title = "Studi Kasus 1: Krisis Rantai Pasok Kopi Rakyat Lereng Gunung Sindoro",
            scenario = "Koperasi Tani 'Maju Makmur' di Lereng Sindoro menaungi 80 petani kopi rakyat dengan total lahan 60 hektar. Masalah kronis yang dihadapi: petani kesulitan memperoleh pupuk berkualitas (hulu), kebiasaan memetik kopi campur hijau-merah (usahatani), menjual cepat ke tengkulak dengan harga murah Rp6.000/kg biji basah karena tidak memiliki fasilitas penjemuran (hilir), serta ketiadaan pencatatan keuangan dan akses kredit perbankan (penunjang). Koperasi bertekad merevitalisasi rantai nilai agribisnis secara menyeluruh.",
            questions = listOf(
                Question(
                    id = "cs1-q1",
                    unitId = "unit-1",
                    tp = TP.TP1,
                    type = QuestionType.MULTIPLE_CHOICE,
                    prompt = "Berdasarkan skenario, masalah kelangkaan pupuk berkualitas dan benih klon unggul merupakan kelemahan pada subsistem:",
                    options = listOf(
                        "Subsistem Usahatani (On-Farm)",
                        "Subsistem Hulu (Upstream / Sarana Produksi)",
                        "Subsistem Hilir (Downstream)",
                        "Subsistem Pemasaran Akhir"
                    ),
                    correctIndex = 1,
                    explanation = "Subsistem hulu mencakup seluruh kegiatan pengadaan sarana produksi seperti benih bersertifikat, pupuk, alsintan, dan pestisida sebelum budidaya dimulai."
                ),
                Question(
                    id = "cs1-q2",
                    unitId = "unit-1",
                    tp = TP.TP1,
                    type = QuestionType.MATCHING,
                    prompt = "Cocokkan setiap subsistem agribisnis dengan langkah solusi perbaikan yang tepat:",
                    pairs = listOf(
                        MatchingPair("Subsistem Hulu", "Penyediaan bibit klon unggul BP 308 & pupuk majemuk"),
                        MatchingPair("Subsistem Usahatani", "Penerapan SOP petik merah seragam 100%"),
                        MatchingPair("Subsistem Hilir", "Pembangunan greenhouse penjemuran & mesin pulper"),
                        MatchingPair("Subsistem Penunjang", "Kemitraan kredit perbankan KUR & sertifikasi Fair Trade")
                    ),
                    explanation = "Integrasi keempat subsistem ini secara sinergis adalah kunci untuk mengubah kopi bernilai rendah menjadi komoditas specialty yang menguntungkan."
                ),
                Question(
                    id = "cs1-q3",
                    unitId = "unit-1",
                    tp = TP.TP1,
                    type = QuestionType.ORDERING,
                    prompt = "Susunlah urutan penerapan fungsi manajemen (POAC) yang harus dilakukan pengurus koperasi:",
                    correctOrder = listOf(
                        "1. Merumuskan target penjualan kopi specialty dan jadwal panen (Planning)",
                        "2. Membagi regu sortasi, operator mesin pulper, dan bendahara (Organizing)",
                        "3. Menggerakkan petani melaksanakan SOP petik merah harian (Actuating)",
                        "4. Memeriksa kadar air penjemuran dan mencatat rendemen mutu (Controlling)"
                    ),
                    explanation = "Fungsi manajemen berjalan runtut dari Perencanaan (Planning) -> Pengorganisasian (Organizing) -> Pelaksanaan (Actuating) -> Pengawasan/Pengendalian (Controlling)."
                ),
                Question(
                    id = "cs1-q4",
                    unitId = "unit-1",
                    tp = TP.TP1,
                    type = QuestionType.MULTIPLE_CHOICE,
                    prompt = "Manakah intervensi di subsistem hilir yang paling efektif melipatgandakan pendapatan petani kopi?",
                    options = listOf(
                        "Menjual langsung seluruh buah kopi glondong basah ke tengkulak luar kota",
                        "Membuat pengolahan pascapanen mandiri (fermentasi & penjemuran) menjadi green bean specialty",
                        "Menambah luas lahan tanam tanpa membenahi proses pengolahan pascapanen",
                        "Menghentikan penggunaan pohon penaung agar kopi lebih cepat masak serempak"
                    ),
                    correctIndex = 1,
                    explanation = "Pengolahan pascapanen yang benar (fermentasi terkontrol dan kadar air 12%) menciptakan nilai tambah (value added) terbesar yang dinikmati langsung oleh petani."
                )
            )
        ),
        CaseStudy(
            id = "cs-2",
            unitId = "unit-2",
            tp = TP.TP2,
            title = "Studi Kasus 2: Anjloknya Produksi Lateks Karet Akibat Cuaca Ekstrem dan Jamur",
            scenario = "Sebuah perkebunan karet seluas 20 hektar di Ogan Komering Ilir mengalami penurunan drastis volume getah lateks sebesar 40% dalam 4 bulan terakhir. Curah hujan bulanan melebihi 350 mm menyebabkan lahan tergenang air karena saluran drainase tersumbat. Hasil pengamatan lapangan menemukan 30% pohon karet menunjukkan gejala daun kusam menguning, gugur prematur, dan terdapat lapisan miselium benang putih tebal di pangkal batang bawah tanah. Uji sampel tanah menunjukkan pH tanah berada di angka 4,1 (sangat masam).",
            questions = listOf(
                Question(
                    id = "cs2-q1",
                    unitId = "unit-2",
                    tp = TP.TP2,
                    type = QuestionType.MULTIPLE_CHOICE,
                    prompt = "Berdasarkan gejala miselium putih tebal di perakaran dan daun menguning, patogen utama yang menyerang kebun karet adalah:",
                    options = listOf(
                        "Jamur Rigidoporus microporus (Jamur Akar Putih / JAP)",
                        "Jamur Hemileia vastatrix (Karat Daun)",
                        "Bakteri Xanthomonas campestris",
                        "Jamur Ganoderma boninense"
                    ),
                    correctIndex = 0,
                    explanation = "Rigidoporus microporus (JAP) adalah momok utama tanaman karet yang membentuk benang putih pada akar dan membusukkan sistem perakaran hingga pohon tumbang."
                ),
                Question(
                    id = "cs2-q2",
                    unitId = "unit-2",
                    tp = TP.TP2,
                    type = QuestionType.MATCHING,
                    prompt = "Pasangkan faktor pembatas produksi dengan langkah agronomis pemulihan kebun karet:",
                    pairs = listOf(
                        MatchingPair("Tanah Tergenang", "Pembuatan parit drainase primer dan sekunder"),
                        MatchingPair("pH Tanah Sangat Masam (4,1)", "Aplikasi kapur pertanian / dolomit 1-2 ton/ha"),
                        MatchingPair("Infeksi Jamur Akar Putih", "Isolasi pohon dengan parit isolasi & fungisida nabati Trichoderma"),
                        MatchingPair("Alur Sadap Basah", "Pemasangan talang pelindung hujan (rain gutter) di bidang sadap")
                    ),
                    explanation = "Tindakan perbaikan harus terpadu mengatasi kelebihan air tanah, menaikkan pH ke rentang ideal, dan mengendalikan patogen JAP secara biologis."
                ),
                Question(
                    id = "cs2-q3",
                    unitId = "unit-2",
                    tp = TP.TP2,
                    type = QuestionType.ORDERING,
                    prompt = "Urutkan tindakan pengendalian terpadu pada pohon karet yang terserang Jamur Akar Putih (JAP):",
                    correctOrder = listOf(
                        "1. Menggali tanah di sekeliling leher akar pohon yang sakit",
                        "2. Mengerok miselium jamur putih dan mengoleskan ter / fungisida",
                        "3. Menaburkan agens hayati Trichoderma harzianum pada lubang galian",
                        "4. Menutup kembali galian dan membuat parit isolasi sedalam 60 cm"
                    ),
                    explanation = "Sanitasi leher akar, pengobatan luka, aplikasi biokontrol Trichoderma, dan isolasi perimeter mencegah penularan spora jamur ke perakaran pohon sehat di sebelahnya."
                ),
                Question(
                    id = "cs2-q4",
                    unitId = "unit-2",
                    tp = TP.TP2,
                    type = QuestionType.MULTIPLE_CHOICE,
                    prompt = "Mengapa penaburan kapur dolomit mutlak diperlukan pada tanah dengan pH 4,1?",
                    options = listOf(
                        "Untuk membunuh cacing tanah yang membuat tanah berpori",
                        "Untuk menetralkan keasaman tanah serta menyediakan unsur hara Kalsium (Ca) dan Magnesium (Mg)",
                        "Agar lateks karet langsung membeku di mangkuk sadap",
                        "Untuk mempercepat gugur daun alami pada pohon karet"
                    ),
                    correctIndex = 1,
                    explanation = "Pada pH < 4,5, unsur aluminium (Al) terlarut meracuni perakaran tanaman. Dolomit (CaMg(CO3)2) menaikkan pH ke tingkat optimal sekaligus menyuplai Ca dan Mg."
                )
            )
        ),
        CaseStudy(
            id = "cs-3",
            unitId = "unit-3",
            tp = TP.TP3,
            title = "Studi Kasus 3: Revitalisasi Kebun Kakao Tua Melalui Sambung Samping Klon Unggul",
            scenario = "Pak Joko memiliki kebun kakao seluas 1,5 hektar yang ditanam 18 tahun lalu menggunakan biji semaian asalan. Saat ini pohon kakao tumbuh sangat tinggi (>5 meter), percabangan tidak teratur, buah sedikit, dan banyak ranting mati mengering karena terserang penyakit Vascular Streak Dieback (VSD). Pak Joko tidak memiliki modal besar untuk membabat habis seluruh pohon dan menanam ulang dari nol (replanting), karena ia masih mengandalkan sedikit pemasukan dari kebun tersebut untuk kebutuhan harian.",
            questions = listOf(
                Question(
                    id = "cs3-q1",
                    unitId = "unit-3",
                    tp = TP.TP3,
                    type = QuestionType.MULTIPLE_CHOICE,
                    prompt = "Kelemahan utama penggunaan bibit semaian biji asalan (generatif tak terkontrol) pada kebun Pak Joko adalah:",
                    options = listOf(
                        "Pohon tidak memiliki perakaran tunggang sama sekali",
                        "Terjadi segregasi genetik sehingga mutu dan produktivitas buah tidak seragam serta rentan penyakit",
                        "Pohon terlalu cepat berbunga sebelum batangnya kokoh",
                        "Biaya pembelian bibit biji asalan jauh lebih mahal daripada bibit okulasi"
                    ),
                    correctIndex = 1,
                    explanation = "Pembiakan generatif dari pohon sembarangan menghasilkan keturunan dengan sifat yang bervariasi luas (segregasi genetik), banyak di antaranya mewarisi sifat rentan penyakit."
                ),
                Question(
                    id = "cs3-q2",
                    unitId = "unit-3",
                    tp = TP.TP3,
                    type = QuestionType.MATCHING,
                    prompt = "Cocokkan teknik pembiakan tanaman berikut dengan karakteristik utamanya:",
                    pairs = listOf(
                        MatchingPair("Sambung Samping (Side Grafting)", "Merehabilitasi pohon kakao tua tanpa menebang pohon utama terlebih dahulu"),
                        MatchingPair("Okulasi Mata Tempel", "Menempelkan mata tunas unggul pada batang bawah semaian bibit karet"),
                        MatchingPair("Stek Satu Ruas Berdaun", "Metode baku perbanyakan klon teh GMB di persemaian"),
                        MatchingPair("Biji Bersertifikat (Tenera)", "Perbanyakan generatif terarah kelapa sawit persilangan Dura x Pisifera")
                    ),
                    explanation = "Sambung samping adalah teknik vegetatif ideal untuk rehabilitasi pohon dewasa tanpa kehilangan fungsi batang bawah yang sudah berakar dalam."
                ),
                Question(
                    id = "cs3-q3",
                    unitId = "unit-3",
                    tp = TP.TP3,
                    type = QuestionType.ORDERING,
                    prompt = "Urutkan langkah pelaksanaan teknik sambung samping pada pohon kakao Pak Joko:",
                    correctOrder = listOf(
                        "1. Memilih entres klon unggul toleran VSD (misal klon MCC 02) yang berkayu setengah matang",
                        "2. Menyayat kulit batang pohon induk kakao setinggi 40-50 cm dari tanah",
                        "3. Menyisipkan entres beririsan miring ke celah kulit batang induk",
                        "4. Mengikat sambungan rapat dengan plastik polietilen dan menyungkupnya kedap air",
                        "5. Memotong tajuk pohon induk secara bertahap setelah tunas sambung tumbuh kuat"
                    ),
                    explanation = "Tahapan sambung samping memastikan pertautan kambium batang bawah dan entres tersambung sempurna sebelum tajuk pohon lama dipangkas."
                ),
                Question(
                    id = "cs3-q4",
                    unitId = "unit-3",
                    tp = TP.TP3,
                    type = QuestionType.MULTIPLE_CHOICE,
                    prompt = "Apa keunggulan terbesar teknik sambung samping bagi kondisi ekonomi Pak Joko?",
                    options = listOf(
                        "Pohon induk lama tetap bisa dipanen sebagian selama tunas baru belum berbuah, dan masa tunggu panen hanya 1-2 tahun",
                        "Tidak memerlukan pisau atau plastik pengikat sambungan",
                        "Pohon kakao baru akan berubah menjadi tanaman semusim",
                        "Kebutuhan pupuk menjadi nol rupiah selamanya"
                    ),
                    correctIndex = 0,
                    explanation = "Sambung samping memanfaatkan sistem perakaran dalam yang sudah mapan, sehingga waktu mulai berbuah jauh lebih cepat (12-18 bulan) dan arus kas petani tidak terputus total."
                )
            )
        ),
        CaseStudy(
            id = "cs-4",
            unitId = "unit-4",
            tp = TP.TP4,
            title = "Studi Kasus 4: Zero Defect Pascapanen Kelapa Sawit dan Keselamatan Kerja di Lahan Gambut",
            scenario = "Afdeling V Perkebunan Kelapa Sawit PT Sawit Lestari beroperasi di atas tanah gambut pasang surut. Manajer kebun menemukan catatan penurunan mutu CPO di pabrik kelapa sawit (PKS) akibat persentase asam lemak bebas (ALB) melonjak hingga 6,2% (standar ekspor < 3%). Penyebabnya: tandan buah segar (TBS) menumpuk di Tempat Pengumpulan Hasil (TPH) lebih dari 48 jam karena jalan poros amblas tergenang. Selain itu, terjadi kecelakaan kerja di mana seorang pemanen terluka robek di bagian kaki akibat terkena bilah egrek saat mengangkat tandan duri tanpa sepatu pelindung.",
            questions = listOf(
                Question(
                    id = "cs4-q1",
                    unitId = "unit-4",
                    tp = TP.TP4,
                    type = QuestionType.MULTIPLE_CHOICE,
                    prompt = "Mengapa penundaan pengiriman TBS ke pabrik selama lebih dari 24 jam menyebabkan lonjakan Asam Lemak Bebas (ALB)?",
                    options = listOf(
                        "Karena air hujan mengubah minyak sawit menjadi glukosa manis",
                        "Enzim lipase aktif memecah trigliserida minyak menjadi asam lemak bebas setelah buah terlepas dari tandan",
                        "Kumbang penyerbuk memakan habis inti sawit",
                        "Tandan buah segar menyerap gas nitrogen dari udara bebas"
                    ),
                    correctIndex = 1,
                    explanation = "Segera setelah buah memar atau terlepas, enzim lipase alami dalam mesokarp sawit menghidrolisis lemak menjadi Asam Lemak Bebas (ALB). Keterlambatan olah merusak mutu CPO."
                ),
                Question(
                    id = "cs4-q2",
                    unitId = "unit-4",
                    tp = TP.TP4,
                    type = QuestionType.MATCHING,
                    prompt = "Pasangkan potensi bahaya kerja di perkebunan sawit dengan APD dan pencegahan K3LH yang tepat:",
                    pairs = listOf(
                        MatchingPair("Bilah Egrek / Dodos Tajam", "Sepatu bot keselamatan (safety boots) bertutup baja & sarung bilah"),
                        MatchingPair("Tertimpa Tandan Buah Berduri", "Helm pelindung keselamatan kerja (safety helmet) bersertifikat"),
                        MatchingPair("Paparan Kabut Semprot Herbisida", "Masker respirator kimia, sarung tangan nitril, dan kacamata goggle"),
                        MatchingPair("Ergonomi Beban Berat Pemanen", "Penggunaan gerobak sorong (angkong) dan teknik angkat bertumpu pada paha")
                    ),
                    explanation = "Penerapan APD lengkap dan teknik ergonomi kerja mutlak untuk mencapai sasaran Zero Accident (Nol Kecelakaan Kerja) di lingkungan perkebunan."
                ),
                Question(
                    id = "cs4-q3",
                    unitId = "unit-4",
                    tp = TP.TP4,
                    type = QuestionType.ORDERING,
                    prompt = "Urutkan alur pascapanen Tandan Buah Segar (TBS) kelapa sawit dari pohon hingga tangki timbun CPO:",
                    correctOrder = listOf(
                        "1. Memanen TBS matang minimal 5 brondolan lepas alami per tandan",
                        "2. Mengangkut tandan dan brondolan bersih ke TPH sebelum jam 12.00",
                        "3. Mengangkut TBS dengan truk ke Pabrik Kelapa Sawit (PKS) dalam tempo < 24 jam",
                        "4. Perebusan uap dalam sterilizer pabrik untuk menghentikan aktivitas enzim lipase",
                        "5. Pengepresan mesokarp untuk memisahkan minyak sawit mentah (CPO)"
                    ),
                    explanation = "Kecepatan rantai pasokan dari pohon ke sterilizer PKS adalah kunci utama menjaga kandungan ALB tetap rendah dan rendemen minyak tinggi."
                ),
                Question(
                    id = "cs4-q4",
                    unitId = "unit-4",
                    tp = TP.TP4,
                    type = QuestionType.MULTIPLE_CHOICE,
                    prompt = "Bagaimanakah pemanfaatan limbah padat Tandan Kosong Sawit (TKS) yang sesuai dengan prinsip kelestarian lingkungan hidup?",
                    options = listOf(
                        "Dibakar di tepi kebun pada malam hari untuk mengusir serangga",
                        "Dibuang ke aliran sungai agar hanyut ke laut",
                        "Diaplikasikan kembali ke piringan tanaman sebagai mulsa organik penyubur tanah dan penahan kelembapan gambut",
                        "Ditumpuk di dekat kantor afdeling hingga membusuk sendiri"
                    ),
                    correctIndex = 2,
                    explanation = "Tandan Kosong Sawit kaya kalium (K) dan bahan organik. Menggunakannya sebagai mulsa organik mengembalikan hara ke tanah serta mencegah pembakaran liar (Zero Burning)."
                )
            )
        )
    )
}
