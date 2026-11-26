package com.example.data.source.content

import com.example.model.LearningUnit
import com.example.model.Media
import com.example.model.Section
import com.example.model.TP

object UnitsContent {
    val units: List<LearningUnit> = listOf(
        LearningUnit(
            id = "unit-1",
            tp = TP.TP1,
            title = "Sistem dan Manajemen Agribisnis Perkebunan",
            summary = "Memahami konsep agribisnis dari hulu ke hilir serta penerapan fungsi POAC dalam pengelolaan komoditas perkebunan.",
            iconName = "business_center",
            quizId = "quiz-unit-1",
            estimatedMinutes = 15,
            sections = listOf(
                Section(
                    id = "u1-s1",
                    heading = "1. Pengertian Sistem Agribisnis",
                    body = "Agribisnis bukan sekadar bercocok tanam (farming), melainkan suatu kesatuan sistem ekonomi berbasis pertanian yang mencakup seluruh rangkaian kegiatan mulai dari pengadaan sarana produksi, budidaya tanaman di kebun, pengolahan hasil panen, hingga distribusi ke tangan konsumen akhir.",
                    keyPoints = listOf(
                        "Agribisnis merupakan sistem terpadu (integrated system).",
                        "Menghubungkan sektor pertanian pedesaan dengan industri manufaktur dan pasar global.",
                        "Efisiensi di setiap mata rantai menentukan profitabilitas usaha perkebunan."
                    ),
                    media = listOf(
                        Media(
                            type = "video",
                            title = "Video Pengantar Sistem Agribisnis Perkebunan",
                            youtubeUrl = "https://www.youtube.com/results?search_query=sistem+agribisnis+perkebunan+smk",
                            caption = "Tinjauan menyeluruh rantai pasok agribisnis modern."
                        )
                    ),
                    infographicId = "agribusiness_subsystems"
                ),
                Section(
                    id = "u1-s2",
                    heading = "2. Empat Subsistem Agribisnis Perkebunan",
                    body = "Sistem agribisnis ditopang oleh 4 pilar subsistem yang saling bergantung:\n\n1. Subsistem Hulu (Upstream): Menyediakan sarana produksi (benih bersertifikat, pupuk, alsintan, pestisida).\n2. Subsistem Usahatani (On-farm): Aktivitas bercocok tanam dan panen di kebun.\n3. Subsistem Hilir (Downstream): Pengolahan primer/sekunder dan pemasaran produk olahan.\n4. Subsistem Penunjang (Supporting): Lembaga perbankan, koperasi, asuransi tani, penyuluhan, dan kebijakan pemerintah.",
                    keyPoints = listOf(
                        "Kegagalan satu subsistem melumpuhkan subsistem lainnya.",
                        "Nilai tambah (value added) terbesar umumnya berada pada subsistem hilir.",
                        "Subsistem penunjang menjamin akses permodalan dan legalitas usaha."
                    ),
                    infographicId = "subsystems_flow"
                ),
                Section(
                    id = "u1-s3",
                    heading = "3. Fungsi Manajemen (POAC) di Perkebunan",
                    body = "Mengelola perkebunan berskala komersial menuntut penerapan manajemen modern:\n\n• Planning (Perencanaan): Menentukan komoditas, varietas, jadwal tanam, dan estimasi anggaran operasional.\n• Organizing (Pengorganisasian): Mengatur struktur mandor, alokasi tenaga kerja harian, dan rotasi alsintan.\n• Actuating (Pelaksanaan): Memimpin dan menggerakkan tim lapangan sesuai SOP teknis budidaya.\n• Controlling (Pengendalian): Mengawasi mutu panen, audit rendemen, dan mencegah kehilangan hasil (losses).",
                    keyPoints = listOf(
                        "Planning meminimalkan risiko ketidakpastian cuaca dan fluktuasi harga.",
                        "Organizing memastikan pembagian tugas kerja afdeling berjalan lancar.",
                        "Controlling mencegah pemborosan sarana produksi dan kebocoran hasil panen."
                    ),
                    infographicId = "poac_cycle"
                ),
                Section(
                    id = "u1-s4",
                    heading = "4. Studi Alur: Dari Biji Kopi hingga Secangkir Kopi",
                    body = "Contoh integrasi agribisnis pada komoditas kopi rakyat:\n• Hulu: Penyediaan bibit klon unggul BP 308 dan pupuk majemuk berimbang.\n• Usahatani: Penanaman naungan lamtoro, pemupukan berkala, dan petik merah seragam.\n• Hilir: Proses fermentasi madu (honey process), penjemuran, pengupasan huller, roasting, hingga diseduh di coffee shop.\n• Penunjang: Koperasi tani kopi mengekspor biji bersertifikat Fair Trade ke pasar Eropa.",
                    keyPoints = listOf(
                        "Petik merah serempak menjamin profil cita rasa kopi specialty.",
                        "Pengolahan pascapanen tepat menaikkan harga jual biji kopi hingga 300%."
                    ),
                    infographicId = "coffee_flow"
                )
            )
        ),
        LearningUnit(
            id = "unit-2",
            tp = TP.TP2,
            title = "Faktor yang Memengaruhi Produksi Tanaman Perkebunan",
            summary = "Menganalisis pengaruh tanah, iklim, bibit unggul, serta strategi pengendalian Organisme Pengganggu Tumbuhan (OPT).",
            iconName = "eco",
            quizId = "quiz-unit-2",
            estimatedMinutes = 15,
            sections = listOf(
                Section(
                    id = "u2-s1",
                    heading = "1. Faktor Tanah dan Kesuburan",
                    body = "Tanah adalah media penyedia unsur hara, air, dan penopang mekanik akar. Sifat tanah yang krusial meliputi:\n• Tekstur & Struktur: Tanah gembur dengan aerasi baik (lempung berpasir) mendukung penetrasi akar.\n• Drainase: Genangan air memicu busuk akar pada kakao dan karet.\n• pH Tanah: Nilai pH ideal sebagian besar perkebunan adalah 5,5 - 6,5. Tanah masam (pH < 5,0) memerlukan aplikasi kapur dolomit untuk menetralkan racun aluminium.",
                    keyPoints = listOf(
                        "pH tanah menentukan ketersediaan unsur hara makro (N, P, K).",
                        "Solum tanah minimal 100 cm diperlukan untuk perakaran tanaman tahunan.",
                        "Bahan organik tanah meningkatkan kapasitas mengikat air."
                    ),
                    infographicId = "soil_ph"
                ),
                Section(
                    id = "u2-s2",
                    heading = "2. Faktor Iklim: Suhu, Curah Hujan & Elevasi",
                    body = "Unsur iklim tidak dapat diubah tetapi harus disesuaikan dengan jenis komoditas:\n• Curah Hujan: Kelapa sawit butuh 2.000-2.500 mm/tahun merata tanpa defisit air > 3 bulan berturut-turut.\n• Suhu & Ketinggian: Kopi Arabika memerlukan udara sejuk dataran tinggi (>1.000 mdpl, 15-24°C), sementara Robusta adaptif di dataran rendah-sedang (400-800 mdpl, 24-30°C).\n• Penyinaran Surya: Menjadi energi utama fotosintesis pembentukan karbohidrat dan minyak nabati.",
                    keyPoints = listOf(
                        "Kesesuaian iklim adalah penentu utama keberhasilan investasi perkebunan.",
                        "Kekeringan ekstrem menyebabkan gugur bunga dan aborsi buah."
                    ),
                    infographicId = "climate_elevation"
                ),
                Section(
                    id = "u2-s3",
                    heading = "3. Faktor Genetik dan Mutu Benih",
                    body = "Penggunaan benih palsu atau asalan adalah kerugian fatal dalam perkebunan tahunan, karena tanaman dirawat puluhan tahun tetapi produktivitasnya rendah. Benih unggul memiliki sertifikat resmi, daya kecambah > 85%, kemurnian varietas terjamin, serta memiliki sifat adaptasi lingkungan dan ketahanan hama penyakit bawaan.",
                    keyPoints = listOf(
                        "Kelapa sawit unggul: Varietas Tenera (persilangan Dura x Pisifera) dengan cangkang tipis dan daging buah tebal.",
                        "Karet unggul: Klon anjuran seperti PB 260 dan IRR 112.",
                        "Klon kopi teruji: Klon BP 308 tahan nematoda parasit."
                    ),
                    infographicId = "seed_quality"
                ),
                Section(
                    id = "u2-s4",
                    heading = "4. Organisme Pengganggu Tumbuhan (OPT)",
                    body = "OPT dikelompokkan menjadi 3 kategori:\n1. Hama: Hewan perusak fisik (mis. Ulat Api pada sawit, Penggerek Buah Kopi/PBKo, kepik Helopeltis pada kakao).\n2. Penyakit: Disebabkan jamur, bakteri, virus (mis. Jamur Akar Putih pada karet, Busuk Pangkal Batang Ganoderma pada sawit, Karat Daun pada kopi).\n3. Gulma: Tumbuhan kompetitor (mis. alang-alang, Mikania) yang merebut hara, air, dan cahaya matahari.",
                    keyPoints = listOf(
                        "Pengendalian Hama Terpadu (PHT) mengutamakan musuh alami dan sanitasi kebun sebelum pestisida kimia.",
                        "Gulma Mikania dapat membelit tanaman muda dan menurunkan hasil hingga 40%."
                    ),
                    infographicId = "opt_types"
                )
            )
        ),
        LearningUnit(
            id = "unit-3",
            tp = TP.TP3,
            title = "Teknik Pembiakan Tanaman Perkebunan",
            summary = "Membandingkan metode pembiakan generatif dan vegetatif serta memilih teknik perbanyakan yang tepat untuk tiap komoditas.",
            iconName = "nature",
            quizId = "quiz-unit-3",
            estimatedMinutes = 15,
            sections = listOf(
                Section(
                    id = "u3-s1",
                    heading = "1. Pembiakan Generatif (Seksual)",
                    body = "Pembiakan generatif terjadi melalui penyerbukan putik dan benang sari yang menghasilkan biji.\n\nKelebihan:\n• Memiliki perakaran tunggang yang kokoh dan dalam, tahan rebah saat angin kencang.\n• Penyimpanan dan transportasi benih relatif mudah dan tahan lama.\n• Biaya bibit lebih terjangkau.\n\nKekurangan:\n• Keturunan mengalami segregasi genetik (sifat tidak 100% seragam dengan induk).\n• Masa remaja (juvenil) lebih panjang, sehingga waktu panen perdana lebih lama.",
                    keyPoints = listOf(
                        "Dipakai luas untuk kelapa sawit bersertifikat hasil persilangan terarah.",
                        "Sering dipakai sebagai batang bawah (rootstock) yang kokoh."
                    ),
                    infographicId = "generative_vs_vegetative"
                ),
                Section(
                    id = "u3-s2",
                    heading = "2. Pembiakan Vegetatif (Aseksual)",
                    body = "Pembiakan vegetatif memanfaatkan organ tubuh tanaman (batang, tunas, mata tempel, daun, atau jaringan sel).\n\nKelebihan:\n• Sifat tanaman anak 100% identik dengan pohon induk unggul (true-to-type).\n• Cepat berbunga dan berbuah (masa TBM lebih pendek 1-2 tahun).\n\nKekurangan:\n• Perakaran serabut cenderung lebih dangkal (kecuali bibit okulasi dengan batang bawah generatif).\n• Membutuhkan keahlian teknis dan biaya pembuatan bibit lebih tinggi.",
                    keyPoints = listOf(
                        "Metode utama untuk menjamin mutu komoditas ekspor.",
                        "Menggabungkan keunggulan ketahanan akar bawah dengan produktivitas mata atas."
                    )
                ),
                Section(
                    id = "u3-s3",
                    heading = "3. Metode Vegetatif Unggulan",
                    body = "Ragam teknik vegetatif di perkebunan:\n• Okulasi (Budding): Menempelkan mata tunas pohon induk ke batang bawah (standar baku pada tanaman karet).\n• Sambung Pucuk / Samping (Grafting): Menyambung pucuk entres produktif pada tanaman kakao atau kopi.\n• Stek Batang/Daun: Menumbuhkan akar dari potongan batang beruas (teknik utama pada perkebunan teh).\n• Cangkok: Mengupas kulit melingkar hingga kambium bersih untuk memicu akar di dahan.\n• Kultur Jaringan: Perbanyakan massal di laboratorium steril berbasis totipotensi sel.",
                    keyPoints = listOf(
                        "Kunci sukses okulasi: kebersihan mata tunas dan pisau tajam steril.",
                        "Ikatan okulasi harus kedap air hujan agar kambium tidak membusuk."
                    ),
                    infographicId = "vegetative_methods"
                ),
                Section(
                    id = "u3-s4",
                    heading = "4. Matriks Pembiakan Komoditas Perkebunan",
                    body = "Rekomendasi metode perbanyakan tanaman perkebunan:\n• Kelapa Sawit: Biji hibrida bersertifikat (D x P) & kultur jaringan (ramet klon).\n• Karet: Okulasi mata tidur / okulasi hijau pada batang bawah semaian.\n• Teh: Stek satu ruas satu daun bertunas (klon GMB).\n• Kakao: Sambung samping (side grafting) & sambung pucuk klon MCC 02.\n• Kopi: Biji untuk batang bawah, sambung pucuk / stek klonal untuk batang atas.",
                    keyPoints = listOf(
                        "Memilih metode yang keliru memperpanjang biaya pembibitan tanpa kepastian panen."
                    )
                )
            )
        ),
        LearningUnit(
            id = "unit-4",
            tp = TP.TP4,
            title = "Produksi, Pascapanen dan K3LH Perkebunan",
            summary = "Penerapan tata kelola kebun terstandar, prosedur panen-pascapanen bernilai tambah, serta keselamatan kerja dan pelestarian lingkungan.",
            iconName = "health_and_safety",
            quizId = "quiz-unit-4",
            estimatedMinutes = 15,
            sections = listOf(
                Section(
                    id = "u4-s1",
                    heading = "1. Tahap Produksi dan Pemeliharaan",
                    body = "Pemeliharaan teratur menentukan masa produktif tanaman selama 20-30 tahun:\n• Pengendalian Gulma: Piringan pohon (weed ring) bersih selebar tajuk tanaman.\n• Pemupukan Berimbang (5T): Tepat Jenis, Tepat Dosis, Tepat Waktu, Tepat Cara, dan Tepat Sasaran.\n• Pemangkasan: Membuang cabang air (wiwilan) dan cabang sakit agar sirkulasi udara lancar dan sinar matahari masuk optimal.",
                    keyPoints = listOf(
                        "Aplikasi pupuk harus disebar di daerah ujung perakaran aktif (dropping zone).",
                        "Pemangkasan sanitasi menekan kelembapan dan perkecambahan spora jamur."
                    ),
                    infographicId = "maintenance_5t"
                ),
                Section(
                    id = "u4-s2",
                    heading = "2. Manajemen Panen: Kriteria Matang Petik",
                    body = "Memanen terlalu awal atau lewat matang menurunkan kualitas hasil secara drastis:\n• Kelapa Sawit: Tandan Buah Segar (TBS) matang minimal jika 5 brondolan buah telah lepas alami per tandan.\n• Kopi: Petik buah merah merata (glondong merah 100%) demi rasa manis alami yang tinggi.\n• Teh: Petik pucuk halus rumus peko (P+2 atau P+3) di pagi hari.\n• Karet: Kedalaman sadap lateks 1,0 - 1,5 mm sebelum kambium, kemiringan torehan 30°.",
                    keyPoints = listOf(
                        "Panen sawit mentah menghasilkan asam lemak bebas tinggi dan rendemen CPO rendah.",
                        "Kedalaman sadap karet yang melukai kambium menyebabkan luka 'kanker kayu' permanen."
                    ),
                    infographicId = "harvest_criteria"
                ),
                Section(
                    id = "u4-s3",
                    heading = "3. Pascapanen: Pengolahan Primer & Penyimpanan",
                    body = "Aktivitas pascapanen bertujuan mengunci kualitas dan memperpanjang masa simpan:\n• Sortasi & Grading: Memisahkan benda asing, buah cacat, dan mengklasifikasikan ukuran mutu.\n• Fermentasi (Kakao/Kopi): Mengembangkan aroma dan rasa khas cokelat/kopi.\n• Pengeringan: Menurunkan kadar air ke batas aman simpan (kopi < 12%, kakao < 7,5%) agar bebas jamur Aspergillus.\n• Penyimpanan: Gudang berventilasi baik, beralas palet kayu, dan terbebas dari hama gudang.",
                    keyPoints = listOf(
                        "Biji kopi atau kakao yang berjamur mengandung mikotoksin dan ditolak pasar ekspor.",
                        "Palet kayu mencegah penyerapan kelembapan dari lantai semen."
                    ),
                    infographicId = "post_harvest_steps"
                ),
                Section(
                    id = "u4-s4",
                    heading = "4. Keselamatan Kerja (K3LH) & Lingkungan",
                    body = "Pekerjaan perkebunan memiliki potensi bahaya fisik, kimiawi, dan biologis:\n• Alat Pelindung Diri (APD) Wajib: Sarung tangan nitril, masker respirator, kacamata google, dan apron saat menyemprot pestisida.\n• Ergonomi Kerja: Menggunakan dodos/egrek bergagang ringan dan postur angkat beban yang benar.\n• Pengelolaan Limbah: Tandan kosong kelapa sawit dijadikan mulsa organik; limbah kulit kopi diolah menjadi pupuk kompos atau teh cascara.",
                    keyPoints = listOf(
                        "Dilarang membersihkan nosel penyemprot yang mampet dengan cara ditiup mulut.",
                        "Wadah bekas pestisida wajib dibilas 3 kali dan ditusuk agar tidak disalahgunakan warga."
                    ),
                    infographicId = "k3lh_safety"
                )
            )
        )
    )
}
