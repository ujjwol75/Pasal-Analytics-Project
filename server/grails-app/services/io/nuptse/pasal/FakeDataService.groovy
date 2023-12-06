package io.nuptse.pasal

import grails.gorm.transactions.Transactional

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Transactional
class FakeDataService {
    def dashboardService
    def serviceMethod(def params) {
        def visitData = []
        def beginDay = new Date().getTime()

        def fakeY = [7, 5, 4, 2, 4, 7, 5, 6, 5, 9, 6, 3, 1, 5, 3, 6, 5]
        for (def i = 0; i < fakeY.size(); i += 1) {
            visitData.add([
                    //new SimpleDateFormat(new Date(), "yyyy-mm-dd"),
                    LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE),
                    fakeY[i]
            ]);
        }

        def visitData2 = []
        def fakeY2 = [1, 6, 4, 8, 3, 7, 2];
        for (def i = 0; i < fakeY2.size(); i += 1) {
            visitData2.add([
                    LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE),
                    fakeY2[i]
            ]);
        }

        /*def salesData = []
        for (def i = 0; i < 12; i += 1) {
            salesData.add([
                x: "${i + 1}moon",
                y: Math.floor(Math.random() * 1000) + 200,
            ]);
        }*/
        def salesData = dashboardService.getSalesData(params.client)

        def searchData = [];
        for (def i = 0; i < 50; i += 1) {
            searchData.add([
                index: i + 1,
                keyword: "search keyword-${i}",
                count: Math.floor(Math.random() * 1000),
                range: Math.floor(Math.random() * 100),
                status: Math.floor((Math.random() * 10) % 2),
            ]);
        }

        def salesTypeData = [
                [
                        x: 'household appliances',
                        y: 4544,
                ],
                [
                        x: 'drinking water',
                        y: 3321,
                ],
                [
                        x: 'personal health',
                        y: 3113,
                ],
                [
                        x: 'Clothing bags',
                        y: 2341,
                ],
                [
                        x: 'Mother and baby products',
                        y: 1231,
                ],
                [
                        x: 'other',
                        y: 1231,
                ],
        ]

        def salesTypeDataOnline = [
                [
                    x: '家用电器',
                    y: 244,
                ],
                [
                    x: '食用酒水',
                    y: 321,
                ],
                [
                    x: '个护健康',
                    y: 311,
                ],
                [
                    x: '服饰箱包',
                    y: 41,
                ],
                [
                    x: '母婴产品',
                    y: 121,
                ],
                [
                    x: '其他',
                    y: 111,
                ],
        ];

        def salesTypeDataOffline = [
                [
                    x: '家用电器',
                    y: 99,
                ],
                [
                    x: '食用酒水',
                    y: 188,
                ],
                [
                    x: '个护健康',
                    y: 344,
                ],
                [
                    x: '服饰箱包',
                    y: 255,
                ],
                [
                    x: '其他',
                    y: 65,
                ],
        ]

        def offlineData = [];
        for (def i = 0; i < 10; i += 1) {
            offlineData.add([
                name: "Stores ${i}",
                cvr: Math.ceil(Math.random() * 9) / 10,
            ]);
        }

        def offlineChartData = [];
        for (def i = 0; i < 20; i += 1) {
            // def date = (new Date().getTime() + 1000 * 60 * 30 * i).format('HH:mm');
            // def date = LocalDate.now().format(DateTimeFormatter.ISO_TIME).toString()
            offlineChartData.add([
                date: LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE),
                type: '客流量',
                value: Math.floor(Math.random() * 100) + 10,
            ])
            offlineChartData.add([
                date: LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE),
                type: '支付笔数',
                value: Math.floor(Math.random() * 100) + 10,
            ]);
        }

        def radarOriginData = [
                [
                    name: '个人',
                    ref: 10,
                    koubei: 8,
                    output: 4,
                    contribute: 5,
                    hot: 7,
                ],
                [
                    name: '团队',
                    ref: 3,
                    koubei: 9,
                    output: 6,
                    contribute: 3,
                    hot: 1,
                ],
                [
                    name: '部门',
                    ref: 4,
                    koubei: 1,
                    output: 6,
                    contribute: 5,
                    hot: 7,
                ],
        ]

        def radarData = [];
        def radarTitleMap = [
            ref: '引用',
            koubei: '口碑',
            output: '产量',
            contribute: '贡献',
            hot: '热度',
        ]

        radarOriginData.each {item->
            item.each {key,v ->
                if(key != 'name') {
                    radarData.add([
                            name: item.name,
                            label: radarTitleMap[key],
                            value: item[key]
                    ])
                }
            }

        }

        def introduceRowData = [
                totalProducts: dashboardService.getTotalProductsCount(params.client),
                totalCustomers : dashboardService.getTotalCustomersCount(params.client),
                totalSalesAmount: dashboardService.getTotalSalesAmount(params.client)
        ]

        def salesRankData = dashboardService.getSalesRankList(params.client)



        def fakeChartData = [
                'visitData': visitData,
                'visitData2': visitData2,
                'salesData': salesData,
                'salesRankData': salesRankData,
                'searchData': searchData,
                'offlineData': offlineData,
                "offlineChartData" : offlineChartData,
                'salesTypeData': salesTypeData,
                'salesTypeDataOnline': salesTypeDataOnline,
                'salesTypeDataOffline': salesTypeDataOffline,
                'radarData': radarData,
                'introduceRowData': introduceRowData
        ]

        return fakeChartData
    }
}
