package com.example.data.source.content

import com.example.model.MatchingPair
import com.example.model.Question
import com.example.model.QuestionType
import com.example.model.TP

object QuizQuestionsContent {

    val unit1Questions: List<Question> = listOf(
        Question(
            id = "u1-q1",
            unitId = "unit-1",
            tp = TP.TP1,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Definisi agribisnis perkebunan yang paling komprehensif adalah:",
            options = listOf(
                "Kegiatan menanam tanaman keras di lahan terbuka sampai menghasilkan uang",
                "Keseluruhan sistem ekonomi terpadu dari penyediaan saprotan, budidaya kebun, pengolahan hasil, hingga pemasaran produk perkebunan",
                "Aktivitas jual beli komoditas ekspor di pasar internasional",
                "Proses memanen dan mengolah kelapa sawit di pabrik"
            ),
            correctIndex = 1,
            explanation = "Agribisnis mencakup seluruh rangkaian sistem dari hulu (saprotan), on-farm (budidaya), hilir (pengolahan & pemasaran), dan subsistem penunjang."
        ),
        Question(
            id = "u1-q2",
            unitId = "unit-1",
            tp = TP.TP1,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Pengadaan benih kelapa sawit bersertifikat dan alat mesin pertanian (alsintan) tergolong ke dalam subsistem:",
            options = listOf(
                "Subsistem Usahatani (On-Farm)",
                "Subsistem Hulu (Upstream)",
                "Subsistem Hilir (Downstream)",
                "Subsistem Pemasaran"
            ),
            correctIndex = 1,
            explanation = "Subsistem hulu bertanggung jawab memproduksi dan mendistribusikan sarana produksi pertanian (saprotan) sebelum budidaya dimulai."
        ),
        Question(
            id = "u1-q3",
            unitId = "unit-1",
            tp = TP.TP1,
            type = QuestionType.MATCHING,
            prompt = "Jodohkan empat pilar subsistem agribisnis dengan contoh kegiatannya:",
            pairs = listOf(
                MatchingPair("Hulu", "Pabrik produsen pupuk NPK & penangkar bibit okulasi karet"),
                MatchingPair("Usahatani", "Pekerjaan penanaman, pemangkasan kopi, dan panen TBS"),
                MatchingPair("Hilir", "Pengolahan lateks menjadi RSS dan penyangraian green bean"),
                MatchingPair("Penunjang", "Penyaluran kredit perbankan KUR dan asuransi usaha tani")
            ),
            explanation = "Masing-masing subsistem memainkan peran spesifik dalam rantai nilai komoditas perkebunan."
        ),
        Question(
            id = "u1-q4",
            unitId = "unit-1",
            tp = TP.TP1,
            type = QuestionType.ORDERING,
            prompt = "Urutkan siklus 4 fungsi manajemen POAC pada operasional kebun:",
            correctOrder = listOf(
                "1. Planning (Perencanaan target panen dan kebutuhan pupuk)",
                "2. Organizing (Pembagian regu kerja dan alokasi mandor kebun)",
                "3. Actuating (Penggerakan tenaga kerja melaksanakan instruksi kerja)",
                "4. Controlling (Pemeriksaan kualitas mutu tandan dan evaluasi biaya)"
            ),
            explanation = "Siklus manajemen dimulai dari Planning, diikuti Organizing, Actuating, dan ditutup dengan Controlling untuk evaluasi."
        ),
        Question(
            id = "u1-q5",
            unitId = "unit-1",
            tp = TP.TP1,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Seorang manajer kebun menyusun rencana kebutuhan tenaga kerja panen dan anggaran pembelian pupuk semester depan. Tindakan ini mencerminkan fungsi manajemen:",
            options = listOf(
                "Controlling",
                "Actuating",
                "Planning",
                "Organizing"
            ),
            correctIndex = 2,
            explanation = "Perumusan sasaran, alokasi anggaran, dan estimasi sumber daya di masa mendatang adalah inti fungsi Planning (Perencanaan)."
        ),
        Question(
            id = "u1-q6",
            unitId = "unit-1",
            tp = TP.TP1,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Mengapa subsistem hilir (pengolahan pascapanen) sangat penting bagi kesejahteraan petani perkebunan?",
            options = listOf(
                "Karena menghasilkan nilai tambah (value added) dan harga jual komoditas yang jauh lebih tinggi daripada bahan mentah",
                "Karena subsistem hilir tidak membutuhkan tenaga kerja manusia",
                "Karena petani tidak perlu lagi memupuk tanamannya",
                "Karena hasil panen bisa dibiarkan membusuk di gudang"
            ),
            correctIndex = 0,
            explanation = "Pengolahan produk (seperti mengolah kopi cherry menjadi green bean specialty atau roast bean) memberikan nilai tambah ekonomi berlipat ganda."
        ),
        Question(
            id = "u1-q7",
            unitId = "unit-1",
            tp = TP.TP1,
            type = QuestionType.MATCHING,
            prompt = "Jodohkan istilah peran manajemen dengan wewenangnya di perkebunan:",
            pairs = listOf(
                MatchingPair("Manajer Kebun", "Menetapkan kebijakan strategis seluruh estate kebun"),
                MatchingPair("Asisten Afdeling", "Mengkoordinir operasional harian 1 afdeling (~800 ha)"),
                MatchingPair("Mandor Lapangan", "Mengawasi langsung kualitas petik pemanen di blok kerja"),
                MatchingPair("Penyuluh (PPL)", "Memberikan edukasi teknik budidaya baru kepada kelompok tani")
            ),
            explanation = "Struktur organisasi perkebunan komersial memiliki hierarki tanggung jawab yang jelas demi efisiensi kerja."
        ),
        Question(
            id = "u1-q8",
            unitId = "unit-1",
            tp = TP.TP1,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Lembaga penunjang yang berperan vital menyerap hasil panen petani anggota dengan harga adil dan menyediakan saprotan bersubsidi adalah:",
            options = listOf(
                "Tengkulak desa perorangan",
                "Koperasi Produsen Pertanian / Koperasi Unit Desa (KUD)",
                "Pedagang perantara luar kota",
                "Pegadaian swasta"
            ),
            correctIndex = 1,
            explanation = "Koperasi pertanian berfungsi menopang daya tawar petani, memotong rantai tengkulak, dan menyediakan kebutuhan sarana produksi."
        )
    )

    val unit2Questions: List<Question> = listOf(
        Question(
            id = "u2-q1",
            unitId = "unit-2",
            tp = TP.TP2,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Rentang pH tanah yang paling ideal bagi sebagian besar tanaman perkebunan tahunan (seperti kelapa sawit dan kakao) adalah:",
            options = listOf(
                "3,0 – 4,0 (Sangat masam)",
                "5,5 – 6,5 (Agak masam hingga netral)",
                "8,5 – 9,5 (Sangat basa)",
                "11,0 – 12,0 (Alkali kuat)"
            ),
            correctIndex = 1,
            explanation = "Pada pH 5,5 - 6,5, ketersediaan hara makro (N, P, K, Ca, Mg) berada pada kondisi optimum dan aktivitas mikroba tanah menguntungkan."
        ),
        Question(
            id = "u2-q2",
            unitId = "unit-2",
            tp = TP.TP2,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Kopi Arabika memerlukan agroklimat spesifik agar menghasilkan cita rasa asam aromatik khas, yaitu:",
            options = listOf(
                "Dataran rendah pantai (0–100 mdpl) dengan suhu 35°C",
                "Dataran tinggi (> 1.000 mdpl) dengan suhu sejuk 15–24°C",
                "Lahan rawa gambut dengan genangan air permanen",
                "Gurun kering dengan curah hujan < 300 mm/tahun"
            ),
            correctIndex = 1,
            explanation = "Ketinggian tempat > 1.000 mdpl memperlambat pematangan buah kopi Arabika sehingga gula dan senyawa aromatik berkembang sempurna."
        ),
        Question(
            id = "u2-q3",
            unitId = "unit-2",
            tp = TP.TP2,
            type = QuestionType.MATCHING,
            prompt = "Jodohkan komoditas perkebunan dengan jenis hama perusak utamanya:",
            pairs = listOf(
                MatchingPair("Kelapa Sawit", "Ulat Api (Setothosea asigna) perusak helaian daun"),
                MatchingPair("Kopi", "Penggerek Buah Kopi / PBKo (Hypothenemus hampei)"),
                MatchingPair("Kakao", "Kepik Penghisap Buah (Helopeltis antonii)"),
                MatchingPair("Karet", "Rayap Tanah (Coptotermes curvignathus) penggerek batang")
            ),
            explanation = "Mengenali hama kunci pada setiap komoditas penting untuk tindakan pengendalian dini terpadu (PHT)."
        ),
        Question(
            id = "u2-q4",
            unitId = "unit-2",
            tp = TP.TP2,
            type = QuestionType.ORDERING,
            prompt = "Urutkan tahapan tindakan Pengendalian Hama Terpadu (PHT) yang benar dari yang ramah lingkungan hingga kimiawi:",
            correctOrder = listOf(
                "1. Sanitasi kebun dan pembersihan gulma inang sarang hama",
                "2. Pemanfaatan musuh alami (predator / parasitoid) atau perangkap feromon",
                "3. Aplikasi agens pengendali hayati (misal jamur Beauveria bassiana)",
                "4. Penyemprotan insektisida kimia selektif sebagai langkah darurat terakhir"
            ),
            explanation = "PHT mengedepankan keseimbangan ekosistem dan hanya memakai pestisida kimia sintetis bila populasi melampaui ambang ekonomi."
        ),
        Question(
            id = "u2-q5",
            unitId = "unit-2",
            tp = TP.TP2,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Tindakan agronomis yang wajib dilakukan jika hasil uji laboratorium menunjukkan tanah kebun sangat masam (pH < 4,5) adalah:",
            options = listOf(
                "Menambahkan belerang murni",
                "Mengaplikasikan kapur pertanian (Kalsit / Dolomit)",
                "Menyiram lahan dengan air garam",
                "Menghentikan seluruh penyiraman air"
            ),
            correctIndex = 1,
            explanation = "Kapur dolomit menetralkan ion H+ dan mengendapkan racun aluminium, sekaligus menyuplai hara esensial Kalsium dan Magnesium."
        ),
        Question(
            id = "u2-q6",
            unitId = "unit-2",
            tp = TP.TP2,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Gulma Mikania micrantha sangat berbahaya di perkebunan kelapa sawit muda karena memiliki sifat:",
            options = listOf(
                "Mengeluarkan getah beracun bagi manusia",
                "Tumbuh merambat cepat membelit bibit dan menutupi cahaya surya (mile-a-minute weed)",
                "Menghasilkan buah yang dimakan serangga penyerbuk",
                "Menyedot seluruh air tanah dalam tempo 1 hari"
            ),
            correctIndex = 1,
            explanation = "Mikania micrantha adalah gulma invasif agresif yang membelit bibit tanaman dan menutupi tajuk sehingga fotosintesis terhambat fatal."
        ),
        Question(
            id = "u2-q7",
            unitId = "unit-2",
            tp = TP.TP2,
            type = QuestionType.MATCHING,
            prompt = "Jodohkan jenis penyakit tanaman perkebunan dengan penyebab patogennya:",
            pairs = listOf(
                MatchingPair("Karat Daun Kopi", "Jamur Hemileia vastatrix"),
                MatchingPair("Jamur Akar Putih (JAP) Karet", "Jamur Rigidoporus microporus"),
                MatchingPair("Busuk Pangkal Batang Sawit", "Jamur Ganoderma boninense"),
                MatchingPair("Vascular Streak Dieback Kakao", "Jamur Ceratobasidium theobromae")
            ),
            explanation = "Mayoritas penyakit utama tanaman perkebunan tropis disebabkan oleh kelompok jamur patogen tular tanah atau tular udara."
        ),
        Question(
            id = "u2-q8",
            unitId = "unit-2",
            tp = TP.TP2,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Mengapa varietas kelapa sawit Tenera paling banyak ditanam di perkebunan komersial dibandingkan varietas Dura atau Pisifera?",
            options = listOf(
                "Karena pohonnya pendek dan tidak berduri",
                "Karena merupakan hibrida dengan cangkang tipis dan daging buah (mesokarp) tebal kaya minyak",
                "Karena tidak membutuhkan pemupukan sama sekali",
                "Karena buahnya berwarna ungu cerah"
            ),
            correctIndex = 1,
            explanation = "Tenera menggabungkan keunggulan Dura dan Pisifera, menghasilkan rendemen minyak mentah kelapa sawit (CPO) tertinggi (22-26%)."
        )
    )

    val unit3Questions: List<Question> = listOf(
        Question(
            id = "u3-q1",
            unitId = "unit-3",
            tp = TP.TP3,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Kelebihan utama perbanyakan tanaman secara generatif (melalui biji) adalah:",
            options = listOf(
                "Sifat buah seragam 100% sama dengan pohon induk",
                "Waktu panen perdana jauh lebih cepat",
                "Memiliki sistem perakaran tunggang yang dalam dan kokoh sehingga tanaman tahan rebah",
                "Bebas dari resiko terkena penyakit tular tanah"
            ),
            correctIndex = 2,
            explanation = "Tanaman dari biji memiliki akar tunggang sejati yang mampu menghunjam dalam ke lapisan tanah, menjadikannya kokoh menahan angin."
        ),
        Question(
            id = "u3-q2",
            unitId = "unit-3",
            tp = TP.TP3,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Mengapa tanaman perkebunan komersial seperti karet dan kakao lebih diprioritaskan diperbanyak secara vegetatif?",
            options = listOf(
                "Agar sifat unggul pohon induk (seperti hasil lateks atau rasa biji) diwariskan 100% identik dan tanaman cepat berbuah",
                "Agar tanaman tumbuh hingga setinggi 50 meter",
                "Supaya tidak memerlukan air penyiraman",
                "Karena perbanyakan vegetatif tidak membutuhkan pisau atau peralatan"
            ),
            correctIndex = 0,
            explanation = "Perbanyakan vegetatif menjamin keseragaman genetik (true-to-type) dan memperpendek masa Tanaman Belum Menghasilkan (TBM)."
        ),
        Question(
            id = "u3-q3",
            unitId = "unit-3",
            tp = TP.TP3,
            type = QuestionType.MATCHING,
            prompt = "Jodohkan komoditas perkebunan dengan metode pembiakan vegetatif utamanya:",
            pairs = listOf(
                MatchingPair("Karet", "Okulasi mata tidur pada batang bawah semaian"),
                MatchingPair("Teh", "Stek satu ruas satu daun bertunas di sungkup"),
                MatchingPair("Kakao", "Sambung samping (side grafting) & sambung pucuk"),
                MatchingPair("Kelapa Sawit", "Kultur jaringan (tissue culture klon ramet)")
            ),
            explanation = "Setiap tanaman memiliki morfologi dan jaringan kambium spesifik yang menentukan teknik perbanyakan paling efektif."
        ),
        Question(
            id = "u3-q4",
            unitId = "unit-3",
            tp = TP.TP3,
            type = QuestionType.ORDERING,
            prompt = "Urutkan langkah pembuatan bibit karet okulasi mata tidur:",
            correctOrder = listOf(
                "1. Menyiapkan batang bawah semaian berdiameter seukuran jempol tangan",
                "2. Membuat jendela sayatan berbentuk U terbalik pada kulit batang bawah",
                "3. Mengambil mata tunas perisai dari kayu entres pohon induk unggul",
                "4. Memasukkan mata tunas ke jendela sayatan dan mengikat rapat dengan pita plastik",
                "5. Membuka ikatan setelah 21 hari untuk memeriksa keberhasilan penempelan (mata hijau segar)"
            ),
            explanation = "Prosedur okulasi karet menuntut kebersihan, ketepatan sayatan kambium, dan ikatan kedap air hujan."
        ),
        Question(
            id = "u3-q5",
            unitId = "unit-3",
            tp = TP.TP3,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Pada teknik sambung pucuk (top grafting) atau sambung samping kakao, bagian tanaman atas yang disambungkan disebut:",
            options = listOf(
                "Batang bawah (Rootstock)",
                "Entres (Scion)",
                "Kambium liar",
                "Kalis"
            ),
            correctIndex = 1,
            explanation = "Entres (scion) adalah cabang tunas muda dari pohon induk unggul yang memiliki mata tunas aktif calon pembentuk tajuk buah."
        ),
        Question(
            id = "u3-q6",
            unitId = "unit-3",
            tp = TP.TP3,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Kunci keberhasilan penyambungan atau okulasi tanaman perkebunan terletak pada bersatunya lapisan:",
            options = listOf(
                "Epidermis luar tanaman",
                "Jaringan kambium batang atas dan kambium batang bawah",
                "Gabus pelindung kulit kayu",
                "Duri pelindung cabang"
            ),
            correctIndex = 1,
            explanation = "Kambium adalah jaringan meristem aktif yang akan membelah membentuk jaringan kalus hingga pembuluh xilem dan floem tersambung menyatu."
        ),
        Question(
            id = "u3-q7",
            unitId = "unit-3",
            tp = TP.TP3,
            type = QuestionType.MATCHING,
            prompt = "Jodohkan istilah perbanyakan dengan penjelasannya:",
            pairs = listOf(
                MatchingPair("Rootstock", "Batang bawah yang menyediakan perakaran kuat dan adaptif"),
                MatchingPair("Scion / Entres", "Pucuk atas yang membawa sifat produksi buah bermutu tinggi"),
                MatchingPair("Eksplan", "Potongan jaringan tanaman steril untuk media kultur jaringan"),
                MatchingPair("Polietilen", "Plastik elastis pengikat sambungan yang kedap air dan lentur")
            ),
            explanation = "Penguasaan istilah teknis pembibitan mendukung ketelitian kerja praktik siswa di persemaian perkebunan."
        ),
        Question(
            id = "u3-q8",
            unitId = "unit-3",
            tp = TP.TP3,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Perbanyakan bibit teh di kebun pembibitan dilakukan dengan stek satu ruas berdaun. Fungsi helai daun yang disisakan pada stek adalah:",
            options = listOf(
                "Sebagai pemberat agar stek tidak tertiup angin",
                "Melakukan fotosintesis menghasilkan energi makanan untuk mendorong inisiasi perakaran baru",
                "Menampung air hujan agar membasahi tanah",
                "Mengusir ulat dan serangga pemakan akar"
            ),
            correctIndex = 1,
            explanation = "Daun tunggal pada stek teh menyuplai asimilat fotosintesis dan auksin alami untuk merangsang pembentukan kalus akar di pangkal ruas."
        )
    )

    val unit4Questions: List<Question> = listOf(
        Question(
            id = "u4-q1",
            unitId = "unit-4",
            tp = TP.TP4,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Kriteria matang panen Tandan Buah Segar (TBS) kelapa sawit yang paling tepat di lapangan adalah:",
            options = listOf(
                "Seluruh kulit buah masih berwarna hijau pekat mengkilap",
                "Minimal 5 brondolan buah telah lepas alami dari tandan ke piringan pohon",
                "Daun kelapa sawit telah mengering seluruhnya",
                "Pelepah di bawah tandan buah sudah membusuk"
            ),
            correctIndex = 1,
            explanation = "Lepasnya 5 butir brondolan membuktikan bahwa kandungan minyak di dalam mesokarp buah sudah mencapai titik maksimal."
        ),
        Question(
            id = "u4-q2",
            unitId = "unit-4",
            tp = TP.TP4,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Rumus pemetikan pucuk daun teh yang menghasilkan kualitas mutu ekspor (peko halus) adalah:",
            options = listOf(
                "P + 5 (Pucuk ditambah 5 daun tua kasar)",
                "P + 2 atau P + 3 (Pucuk ditambah 2 atau 3 daun muda teratas)",
                "Memotong batang teh setinggi 50 cm dari tanah",
                "Memetik daun tua yang telah gugur ke tanah"
            ),
            correctIndex = 1,
            explanation = "Pucuk peko muda (P+2 atau P+3) kaya akan polifenol, kafein, dan minyak atsiri serta memiliki serat kasar yang sangat rendah."
        ),
        Question(
            id = "u4-q3",
            unitId = "unit-4",
            tp = TP.TP4,
            type = QuestionType.MATCHING,
            prompt = "Jodohkan tahapan pascapanen komoditas perkebunan dengan fungsinya:",
            pairs = listOf(
                MatchingPair("Sortasi Rambang Kopi", "Memisahkan buah kopi hampa/rusak yang mengapung di air"),
                MatchingPair("Fermentasi Kakao", "Menghilangkan lendir pulpa dan memicu pembentukan aroma cokelat"),
                MatchingPair("Pelayuan Daun Teh", "Menurunkan kadar air daun 50% agar lentur saat digulung"),
                MatchingPair("Koagulasi Lateks", "Membekukan getah karet cair menggunakan asam format")
            ),
            explanation = "Setiap perlakuan pascapanen memiliki landasan ilmiah kimiawi untuk menyempurnakan kualitas bahan mentah."
        ),
        Question(
            id = "u4-q4",
            unitId = "unit-4",
            tp = TP.TP4,
            type = QuestionType.ORDERING,
            prompt = "Urutkan tahapan pengolahan pascapanen basah (wet process) pada buah kopi:",
            correctOrder = listOf(
                "1. Sortasi rambang buah merah di bak air",
                "2. Pengupasan kulit buah merah menggunakan mesin pulper",
                "3. Fermentasi biji kopi berkulit tanduk selama 12–36 jam",
                "4. Pencucian lendir biji dengan air bersih",
                "5. Penjemuran di lantai jemur hingga kadar air 12%"
            ),
            explanation = "Metode basah (full washed) menghasilkan profil rasa kopi yang bersih (clean cup) dengan keasaman cerah yang disukai pasar specialty."
        ),
        Question(
            id = "u4-q5",
            unitId = "unit-4",
            tp = TP.TP4,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Prinsip pemupukan berimbang '5 Tepat' di perkebunan mencakup:",
            options = listOf(
                "Tepat Harga, Tepat Merek, Tepat Warna, Tepat Bau, Tepat Rasa",
                "Tepat Jenis, Tepat Dosis, Tepat Waktu, Tepat Cara, Tepat Sasaran",
                "Tepat Banyak, Tepat Cepat, Tepat Murah, Tepat Mudah, Tepat Laku",
                "Tepat Musim, Tepat Cuaca, Tepat Pabrik, Tepat Mandor, Tepat Truk"
            ),
            correctIndex = 1,
            explanation = "5T menjamin tanaman memperoleh hara yang tepat tanpa pemborosan biaya pupuk dan tanpa mencemari air tanah."
        ),
        Question(
            id = "u4-q6",
            unitId = "unit-4",
            tp = TP.TP4,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Batas kadar air maksimal biji kopi kering sebelum disimpan di gudang agar tidak ditumbuhi jamur beracun adalah:",
            options = listOf(
                "25 – 30%",
                "11 – 12%",
                "0,5 – 1%",
                "40 – 50%"
            ),
            correctIndex = 1,
            explanation = "Kadar air 11-12% adalah standar internasional batas aman aktivitas air (water activity) untuk menghambat kapang Aspergillus ochraceus."
        ),
        Question(
            id = "u4-q7",
            unitId = "unit-4",
            tp = TP.TP4,
            type = QuestionType.MATCHING,
            prompt = "Jodohkan pekerjaan di perkebunan dengan APD K3LH wajibnya:",
            pairs = listOf(
                MatchingPair("Aplikasi Herbisida", "Masker respirator, sarung tangan nitril, apron, & goggle"),
                MatchingPair("Pemanenan Sawit (Egrek)", "Helm keselamatan (safety helmet) & kacamata pelindung serpihan"),
                MatchingPair("Penyadapan Karet", "Sepatu bot karet anti-selip & lampu senter kepala pagi hari"),
                MatchingPair("Pembersihan Lahan Semak", "Sepatu bot bertutup baja (safety shoes) & sarung tangan kulit")
            ),
            explanation = "Kepatuhan APD merupakan benteng utama keselamatan jiwa tenaga kerja perkebunan."
        ),
        Question(
            id = "u4-q8",
            unitId = "unit-4",
            tp = TP.TP4,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Mengapa pembukaan lahan perkebunan dengan cara membakar hutan/semak (slash and burn) dilarang keras oleh hukum dan prinsip lingkungan?",
            options = listOf(
                "Karena abu bakaran membuat tanah menjadi terlalu subur",
                "Karena merusak struktur tanah, membunuh mikroba bermanfaat, memicu kebakaran hutan meluas, dan mencemari udara dengan kabut asap",
                "Karena abu bakaran menghabiskan persediaan air sumur warga",
                "Karena pohon yang dibakar akan tumbuh kembali menjadi rumput liar"
            ),
            correctIndex = 1,
            explanation = "Metode Zero Burning (pembukaan lahan tanpa bakar) menjaga kelestarian karbon gambut, mikroorganisme tanah, dan mencegah bencana asap."
        )
    )

    // 10 Pretest Questions (comprehensive diagnostik test covering TP 1 - TP 4)
    val pretestQuestions: List<Question> = listOf(
        Question(
            id = "pre-1",
            unitId = "unit-1",
            tp = TP.TP1,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Empat pilar subsistem dalam kesatuan agribisnis perkebunan secara berurutan adalah:",
            options = listOf(
                "Bibit, Tanah, Pupuk, Pemasaran",
                "Hulu, Usahatani (On-farm), Hilir, dan Penunjang",
                "Perencanaan, Pelaksanaan, Pengawasan, Evaluasi",
                "Penanaman, Pemupukan, Pemanenan, Penjualan"
            ),
            correctIndex = 1,
            explanation = "Sistem agribisnis membagi rantai ekonomi menjadi subsistem hulu, usahatani, hilir, dan subsistem penunjang."
        ),
        Question(
            id = "pre-2",
            unitId = "unit-1",
            tp = TP.TP1,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Aktivitas manajer kebun menentukan jadwal pemupukan dan alokasi anggaran operasional semesteran tergolong fungsi manajemen:",
            options = listOf(
                "Organizing",
                "Planning",
                "Controlling",
                "Actuating"
            ),
            correctIndex = 1,
            explanation = "Perencanaan (Planning) mencakup perumusan sasaran, alokasi biaya, dan penjadwalan kerja."
        ),
        Question(
            id = "pre-3",
            unitId = "unit-2",
            tp = TP.TP2,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Derajat keasaman tanah (pH) optimal untuk budidaya kelapa sawit dan kakao adalah:",
            options = listOf(
                "pH 3,0 – 3,8",
                "pH 5,5 – 6,5",
                "pH 8,5 – 9,5",
                "pH 12,0"
            ),
            correctIndex = 1,
            explanation = "Tanah agak masam hingga mendekati netral (pH 5,5 - 6,5) menyediakan hara makro seimbang."
        ),
        Question(
            id = "pre-4",
            unitId = "unit-2",
            tp = TP.TP2,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Hama pemakan daun yang sering menimbulkan kerugian fatal pada perkebunan kelapa sawit adalah:",
            options = listOf(
                "Ulat Api (Setothosea asigna)",
                "Kepik Helopeltis",
                "Kutu Daun Putih",
                "Lalat Buah Dacus"
            ),
            correctIndex = 0,
            explanation = "Ulat api dapat memakan habis daun kelapa sawit dalam waktu singkat sehingga tanaman gundul."
        ),
        Question(
            id = "pre-5",
            unitId = "unit-2",
            tp = TP.TP2,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Agroklimat dataran tinggi (> 1.000 mdpl) dengan suhu sejuk 15–24°C sangat dibutuhkan untuk komoditas:",
            options = listOf(
                "Kopi Robusta",
                "Kelapa Sawit",
                "Kopi Arabika",
                "Karet Klon PB 260"
            ),
            correctIndex = 2,
            explanation = "Kopi Arabika memerlukan dataran tinggi berhawa sejuk untuk membentuk profil rasa specialty."
        ),
        Question(
            id = "pre-6",
            unitId = "unit-3",
            tp = TP.TP3,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Kelebihan utama perbanyakan tanaman perkebunan secara generatif (melalui biji) adalah:",
            options = listOf(
                "Keturunan 100% persis induknya",
                "Sistem perakaran tunggang yang kokoh dan tahan rebah",
                "Masa berbuah lebih cepat 2 tahun",
                "Tidak membutuhkan tanah sebagai media"
            ),
            correctIndex = 1,
            explanation = "Biji menumbuhkan akar tunggang sejati yang menembus tanah dalam."
        ),
        Question(
            id = "pre-7",
            unitId = "unit-3",
            tp = TP.TP3,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Perbanyakan tanaman karet komersial di perkebunan umumnya menggunakan teknik vegetatif:",
            options = listOf(
                "Cangkok batang",
                "Stek pucuk daun",
                "Okulasi mata tidur pada batang bawah",
                "Kultur embrio biji asalan"
            ),
            correctIndex = 2,
            explanation = "Okulasi menggabungkan batang bawah semaian yang berakar kuat dengan mata tunas klon lateks unggul."
        ),
        Question(
            id = "pre-8",
            unitId = "unit-3",
            tp = TP.TP3,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Perbanyakan vegetatif tanaman teh di kebun persemaian dilakukan dengan:",
            options = listOf(
                "Biji buah teh matang",
                "Stek satu ruas satu daun bertunas",
                "Pemisahan anakan liar",
                "Sambung celah akar"
            ),
            correctIndex = 1,
            explanation = "Stek satu ruas daun bertunas merupakan metode baku perbanyakan massal klon teh GMB."
        ),
        Question(
            id = "pre-9",
            unitId = "unit-4",
            tp = TP.TP4,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Kriteria matang panen Tandan Buah Segar (TBS) kelapa sawit adalah minimal ada buah lepas alami sebanyak:",
            options = listOf(
                "50 brondolan",
                "5 brondolan di piringan pohon",
                "1 pohon penuh gugur",
                "Tidak ada brondolan lepas"
            ),
            correctIndex = 1,
            explanation = "Standar kematangan TBS kelapa sawit adalah minimal 5 brondolan lepas alami per tandan."
        ),
        Question(
            id = "pre-10",
            unitId = "unit-4",
            tp = TP.TP4,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Tujuan utama proses fermentasi pada biji kakao dan kopi selama pascapanen adalah:",
            options = listOf(
                "Membuat biji bertambah berat 2 kali lipat",
                "Mengembangkan prekursor aroma dan cita rasa khas serta menghilangkan lendir pulpa",
                "Membunuh seluruh bakteri di dalam tanah",
                "Mengubah biji menjadi tepung cokelat instan"
            ),
            correctIndex = 1,
            explanation = "Fermentasi menguraikan senyawa polifenol dan gula pulpa menjadi senyawa prekursor aroma cokelat/kopi."
        )
    )

    // 10 Posttest Questions (parallel equivalent items to assess competency improvement and calculate N-Gain)
    val posttestQuestions: List<Question> = listOf(
        Question(
            id = "post-1",
            unitId = "unit-1",
            tp = TP.TP1,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Koperasi yang menyediakan sarana kredit permodalan bagi petani kelapa sawit rakyat berkedudukan pada subsistem:",
            options = listOf(
                "Subsistem Usahatani (On-farm)",
                "Subsistem Hilir",
                "Subsistem Penunjang (Supporting)",
                "Subsistem Budidaya Primer"
            ),
            correctIndex = 2,
            explanation = "Perbankan, koperasi kredit, dan asuransi pertanian merupakan pilar subsistem penunjang agribisnis."
        ),
        Question(
            id = "pre-2-post",
            unitId = "unit-1",
            tp = TP.TP1,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Asisten kebun memeriksa buku rendemen TBS dan menindak pemanen yang memetik buah mentah. Hal ini mencerminkan fungsi manajemen:",
            options = listOf(
                "Planning",
                "Controlling (Pengawasan & Pengendalian)",
                "Organizing",
                "Motivating"
            ),
            correctIndex = 1,
            explanation = "Membandingkan hasil kerja aktual dengan standar mutu SOP adalah esensi fungsi Controlling."
        ),
        Question(
            id = "post-3",
            unitId = "unit-2",
            tp = TP.TP2,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Jika tanah kebun perkebunan memiliki pH 4,0 (sangat masam), upaya tepat untuk menaikkan pH sekaligus menambah unsur Mg adalah:",
            options = listOf(
                "Memberi pupuk ZA dosis tinggi",
                "Menaburkan kapur dolomit secara merata",
                "Menyiram air asam cuka",
                "Menggenangi lahan dengan air rawa"
            ),
            correctIndex = 1,
            explanation = "Dolomit mengandung Ca dan Mg yang menaikkan pH tanah masam ke batas ideal 5,5 - 6,5."
        ),
        Question(
            id = "post-4",
            unitId = "unit-2",
            tp = TP.TP2,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Hama kepik penghisap yang menusuk buah kakao muda hingga berbercak hitam berkerut adalah:",
            options = listOf(
                "Helopeltis antonii",
                "Setothosea asigna",
                "Hypothenemus hampei",
                "Oryctes rhinoceros"
            ),
            correctIndex = 0,
            explanation = "Helopeltis menusukkan alat mulutnya dan mengeluarkan racun saliva yang merusak jaringan buah kakao muda."
        ),
        Question(
            id = "post-5",
            unitId = "unit-2",
            tp = TP.TP2,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Kopi Robusta memiliki keunggulan adaptasi iklim dibanding Arabika, yaitu mampu berproduksi optimal di:",
            options = listOf(
                "Puncak gunung beriklim salju (< 0°C)",
                "Dataran rendah hingga sedang (400–800 mdpl) dengan suhu lebih hangat 24–30°C",
                "Gurun pasir tandus tanpa air",
                "Ketinggian > 2.500 mdpl berkabut tebal"
            ),
            correctIndex = 1,
            explanation = "Robusta lebih toleran terhadap suhu yang lebih hangat di dataran rendah hingga sedang."
        ),
        Question(
            id = "post-6",
            unitId = "unit-3",
            tp = TP.TP3,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Kelemahan utama pembiakan generatif tanaman perkebunan dari biji yang tidak terkontrol adalah:",
            options = listOf(
                "Tanaman tidak memiliki daun",
                "Terjadi segregasi genetik sehingga produksi buah anak tidak seragam dan berbeda dari induk",
                "Tanaman menjadi terlalu pendek",
                "Biaya pembelian biji selalu paling mahal"
            ),
            correctIndex = 1,
            explanation = "Peleburan gamet jantan dan betina memunculkan variasi genetik baru (segregasi), sering kali menurunkan mutu hasil."
        ),
        Question(
            id = "post-7",
            unitId = "unit-3",
            tp = TP.TP3,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Kunci keberhasilan teknik sambung samping (side grafting) kakao adalah bertemunya lapisan aktif:",
            options = listOf(
                "Kambium entres dengan kambium batang bawah",
                "Kulit ari luar dengan kayu empulur",
                "Bunga kakao dengan serbuk sari",
                "Plastik lilitan dengan daun peneduh"
            ),
            correctIndex = 0,
            explanation = "Kambium adalah satu-satunya jaringan meristematik yang mampu membelah dan menyatukan sambungan bibit."
        ),
        Question(
            id = "post-8",
            unitId = "unit-3",
            tp = TP.TP3,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Perbanyakan massal bibit kelapa sawit unggul bebas variasi di laboratorium modern dilakukan melalui:",
            options = listOf(
                "Cangkok dahan",
                "Kultur jaringan (tissue culture klon ramet)",
                "Stek akar",
                "Penyiraman zat kimia pengatur tumbuh di kebun"
            ),
            correctIndex = 1,
            explanation = "Kultur jaringan memperbanyak sel klonal pohon induk kelapa sawit pilihan secara massal dan seragam."
        ),
        Question(
            id = "post-9",
            unitId = "unit-4",
            tp = TP.TP4,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Mengapa pengangkutan TBS kelapa sawit ke pabrik (PKS) wajib dilakukan dalam tempo kurang dari 24 jam?",
            options = listOf(
                "Agar truk pengangkut tidak berdebu",
                "Mencegah kenaikan kadar Asam Lemak Bebas (ALB) akibat hidrolisis enzim lipase",
                "Supaya bobot buah bertambah berat di jalan",
                "Untuk menghindari pungutan liar di jalan raya"
            ),
            correctIndex = 1,
            explanation = "Enzim lipase langsung merusak trigliserida menjadi ALB begitu buah terlepas. Keterlambatan olah menurunkan kualitas CPO."
        ),
        Question(
            id = "post-10",
            unitId = "unit-4",
            tp = TP.TP4,
            type = QuestionType.MULTIPLE_CHOICE,
            prompt = "Pekerja yang bertugas menyemprot herbisida di blok gulma kebun sawit wajib mengenakan APD lengkap berupa:",
            options = listOf(
                "Topi jerami dan sandal jepit",
                "Masker respirator kimia, sarung tangan nitril, apron pelindung, dan sepatu bot karet",
                "Kacamata hitam gaya dan kaus santai",
                "Rompi pelampung dan helm sepeda"
            ),
            correctIndex = 1,
            explanation = "Herbisida bersifat racun kontak dan sistemik; masker respirator, sarung tangan nitril, dan apron mencegah penyerapan racun melalui kulit dan pernapasan."
        )
    )
}
