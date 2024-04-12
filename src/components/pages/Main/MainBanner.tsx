import { MainBannerData } from '@/types/MainBannerDataType'
import Image from 'next/image'
function MainBanner() {
    return (
        <section className="pt-4 px-4">
            <div className=" relative w-full h-24 ">
                <Image
                    src={
                        'https://simg.ssgcdn.com/trans.ssg?src=/cmpt/banner/202307/2023072514344365854650363565_696.jpg&w=750&h=0'
                    }
                    alt="메인베너"
                    fill
                />
            </div>
            <div className="mt-2 ">
                <p className="font-bold text-lg"> 카드 할인받고 즐겁게 쇼핑해요</p>
                <p className="text-xs">SSGPAY로 결제하셔도 혜택 받을 수 있어요</p>
                <div className="flex overflow-x-scroll gap-x-3">
                    {MainBannerData.map((item, idx) => (
                        <div
                            key={idx}
                            className="p-3 mt-3 "
                            style={{
                                background: item.background,
                                height: '100px',
                                marginBottom: '20px',
                                minWidth: '150px',
                            }}
                        >
                            <h2 className="text-white font-bold">{item.title1}</h2>
                            <h3 className="text-white text-sm mt-2">{item.title2}</h3>
                            <p className="text-gray-100 text-xs">{item.subTitle}</p>
                        </div>
                    ))}
                </div>
            </div>
        </section>
    )
}
export default MainBanner
