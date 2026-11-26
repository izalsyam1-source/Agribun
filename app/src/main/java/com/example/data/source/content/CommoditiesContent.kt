package com.example.data.source.content

import com.example.model.Commodity

object CommoditiesContent {
    val commodities: List<Commodity> = listOf(
        Commodity(
            id = "kopi",
            name = "Kopi",
            latinName = "Coffea sp.",
            emoji = "☕",
            description = "Tanaman semak perkebunan bernilai ekonomi tinggi yang bijinya disangrai dan digiling menjadi minuman seduh terpopuler di dunia.",
            growthRequirements = listOf(
                "Ketinggian: Arabika (1.000–2.000 mdpl), Robusta (400–800 mdpl)",
                "Suhu optimal: 15–24°C (Arabika), 24–30°C (Robusta)",
                "Curah hujan: 1.500–2.500 mm/tahun dengan 2–3 bulan kering untuk pembungaan",
                "Tanah: Kaya bahan organik, drainase baik, pH 5,5–6,5",
                "Naungan: Membutuhkan pohon peneduh (Lamtoro/Sengon) pada tanaman muda"
            ),
            propagation = "Generatif (biji pilihan untuk batang bawah) & Vegetatif (sambung pucuk/top grafting dan stek klon unggul BP 308).",
            productionStages = listOf(
                "Pembibitan bedengan dan polibeg (6–9 bulan)",
                "Persiapan lubang tanam dan tanaman penaung sementara",
                "Tanaman Belum Menghasilkan / TBM (1–3 tahun) dengan pemangkasan bentuk",
                "Tanaman Menghasilkan / TM (Mulai tahun ke-3 hingga 25+ tahun) dengan pemangkasan produksi dan wiwilan"
            ),
            postHarvest = listOf(
                "Petik merah serentak (cherry red 100%)",
                "Sortasi rambang di air (memisahkan buah hampa mengapung)",
                "Proses pengolahan: Basah (Full Washed), Semi-basah (Wet Hulled), Honey, atau Kering (Natural)",
                "Penjemuran hingga kadar air stabil 11–12%",
                "Pengupasan kulit tanduk (hulling) dan sortasi biji hijau (green beans)"
            ),
            funFact = "Kopi merupakan komoditas ekspor agribisnis paling aktif diperdagangkan di bursa komoditas dunia setelah minyak bumi mentah!"
        ),
        Commodity(
            id = "karet",
            name = "Karet",
            latinName = "Hevea brasiliensis",
            emoji = "🌳",
            description = "Pohon penghasil getah lateks alami yang menjadi bahan baku vital industri ban kendaraan, sarung tangan medis, dan elastisitas industri.",
            growthRequirements = listOf(
                "Ketinggian: 0–600 mdpl (optimal < 300 mdpl)",
                "Suhu optimal: 25–30°C dengan kelembapan tinggi",
                "Curah hujan: 2.000–2.500 mm/tahun tanpa kemarau panjang",
                "Tanah: Podsolik merah kuning atau alluvial, aerasi dalam, pH 4,5–6,5",
                "Penyinaran: Minimal 5–6 jam penyinaran matahari langsung per hari"
            ),
            propagation = "Vegetatif melalui okulasi mata tidur atau okulasi hijau pada batang bawah semaian biji vigour (klon anjuran PB 260, IRR 112, BPM 24).",
            productionStages = listOf(
                "Pembibitan okulasi polibeg (8–12 bulan)",
                "Penanaman lapangan jarak 6 x 3 m (kerapatan ~550 pohon/ha)",
                "TBM (4,5–5 tahun) dengan penyiangan gulma dan pemupukan intensif",
                "Buka sadap perdana: Lilit batang ≥ 45 cm pada ketinggian 100 cm dari pertautan okulasi",
                "Masa penyadapan ekonomis: 25–30 tahun"
            ),
            postHarvest = listOf(
                "Penyadapan subuh (jam 05.00–07.30) saat tekanan turgor sel lateks maksimal",
                "Kemiringan alur sadap 30° dengan kedalaman 1–1,5 mm dari kambium",
                "Pengumpulan lateks cair dan penambahan koagulan asam format/semut",
                "Penggilingan bekuan menjadi sheet (karet sit asap RSS atau sit angin)"
            ),
            funFact = "Getah lateks mengalir paling deras saat fajar karena tekanan turgor sel getah berada pada titik tertinggi sebelum daun berfotosintesis dan terjadi transpirasi."
        ),
        Commodity(
            id = "teh",
            name = "Teh",
            latinName = "Camellia sinensis",
            emoji = "🍵",
            description = "Tanaman perdu dataran tinggi yang pucuk daunnya diolah menjadi aneka teh menyehatkan kaya antioksidan polifenol.",
            growthRequirements = listOf(
                "Ketinggian: 800–2.000 mdpl (semakin tinggi, aroma teh semakin pekat)",
                "Suhu optimal: 13–25°C dengan kabut dan kelembapan > 70%",
                "Curah hujan: > 2.000 mm/tahun merata tanpa bulan kering berkepanjangan",
                "Tanah: Andosol atau Regosol vulkanik yang gembur, kaya humus, pH masam 4,5–5,5"
            ),
            propagation = "Vegetatif dengan stek satu ruas satu daun bertunas dari kebun induk klon unggul (GMB 7, TRI 2025).",
            productionStages = listOf(
                "Persemaian sungkup plastik selama 6–8 bulan",
                "Penanaman mengikuti garis kontur bukit (terasering)",
                "TBM (2–3 tahun) dengan pemangkasan bertahap untuk membentuk 'bidang petik' (plucking table)",
                "Siklus pemetikan rutin setiap 7–14 hari"
            ),
            postHarvest = listOf(
                "Petik pucuk halus dengan rumus peko: P+2 (pucuk + 2 daun muda) atau P+3",
                "Pelayuan (withering) untuk mengurangi kadar air daun 50%",
                "Penggulungan (rolling) untuk memecahkan sel daun agar getah keluar",
                "Fermentasi/Oksidasi enzimatis (untuk teh hitam) atau pelayuan uap panas (untuk teh hijau)",
                "Pengeringan dan sortasi bubuk teh berdasarkan ukuran partikel"
            ),
            funFact = "Teh hitam, teh hijau, dan teh oolong semuanya dipetik dari tanaman yang sama persis (Camellia sinensis), perbedaannya semata-mata terletak pada tingkat oksidasi enzimatis pascapanen!"
        ),
        Commodity(
            id = "kelapa-sawit",
            name = "Kelapa Sawit",
            latinName = "Elaeis guineensis",
            emoji = "🌴",
            description = "Palma tropis penghasil minyak mentah nabati (CPO & PKO) dengan produktivitas minyak per hektar tertinggi di dunia.",
            growthRequirements = listOf(
                "Ketinggian: 0–400 mdpl (optimal dataran rendah tropis basah)",
                "Suhu rata-rata: 24–28°C",
                "Curah hujan: 2.000–2.500 mm/tahun terdistribusi merata",
                "Penyinaran matahari: Minimal 5–7 jam/hari untuk sintesis minyak di mesokarp",
                "Tanah: Latosol, Podsolik, dan Alluvial dengan drainase baik, pH 5,0–6,5"
            ),
            propagation = "Generatif terarah menggunakan benih kecambah bersertifikat varietas Tenera (persilangan Dura x Pisifera).",
            productionStages = listOf(
                "Pre-nursery polibeg kecil (3 bulan)",
                "Main-nursery polibeg besar (9–12 bulan) dengan seleksi bibit afkir ketat",
                "TBM (3 tahun) dengan penanaman kacangan penutup tanah (LCC)",
                "TM (Tahun ke-4 hingga 25 tahun) dengan penyerbukan dibantu kumbang Elaeidobius kamerunicus"
            ),
            postHarvest = listOf(
                "Panen Tandan Buah Segar (TBS) dengan kriteria matang: Minimal 5 brondolan lepas alami per tandan",
                "Pengumpulan brondolan buah di Tempat Pengumpulan Hasil (TPH)",
                "Pengiriman ke Pabrik Kelapa Sawit (PKS) dalam tempo < 24 jam untuk mencegah kenaikan kadar Asam Lemak Bebas (ALB)",
                "Pengolahan pabrik: Sterilisasi uap, perontokan, pemerasan minyak mesokarp (CPO), dan pemisahan inti sawit (PKO)"
            ),
            funFact = "Kelapa sawit menghasilkan minyak hingga 4–5 ton per hektar per tahun, sekitar 5 hingga 8 kali lebih efisien memanfaatkan lahan dibandingkan kedelai, bunga matahari, atau kanola."
        ),
        Commodity(
            id = "kakao",
            name = "Kakao",
            latinName = "Theobroma cacao",
            emoji = "🍫",
            description = "Pohon buah tropis penghasil biji cokelat yang kaya flavonoid dan mentega kakao alami, bahan pokok cokelat dunia.",
            growthRequirements = listOf(
                "Ketinggian: 0–800 mdpl (optimal 100–500 mdpl)",
                "Suhu optimal: 25–30°C",
                "Curah hujan: 1.500–2.000 mm/tahun tanpa angin kencang",
                "Naungan: Memerlukan naungan tetap (30–40%) seperti pohon Gliricidia (Gamal) atau kelapa",
                "Tanah: Tekstur lempung berpasir gembur, kedalaman solum > 1,5 m, pH 6,0–7,0"
            ),
            propagation = "Vegetatif dengan sambung pucuk atau sambung samping (side grafting) menggunakan entres klon unggul toleran Vascular Streak Dieback (VSD) seperti klon MCC 02.",
            productionStages = listOf(
                "Pembibitan polibeg di bawah naungan (4–6 bulan)",
                "Penanaman pola tumpangsari dengan pohon pelindung",
                "Pemangkasan bentuk, pemangkasan produksi (pembuangan tunas air/chupon), dan pemangkasan sanitasi",
                "Masa panen puncak dua kali setahun (panen raya)"
            ),
            postHarvest = listOf(
                "Pemetikan buah masak dengan kriteria warna kulit kuning/oranye menggunakan gunting pangkas",
                "Pemecahan kulit buah dengan pemukul kayu (tidak memakai parang besi agar biji tidak terluka)",
                "Fermentasi wajib dalam kotak kayu berventilasi selama 4–5 hari dengan pembalikan berkala",
                "Penjemuran di bawah sinar matahari hingga kadar air 7,5%",
                "Sortasi biji kering berdasarkan kadar biji pipih dan jamur"
            ),
            funFact = "Nama genus 'Theobroma' berasal dari bahasa Yunani yang secara harfiah bermakna 'Makanan Para Dewa' (Food of the Gods) karena kelezatan dan khasiatnya yang luar biasa."
        )
    )
}
