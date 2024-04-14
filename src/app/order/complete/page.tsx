import Image from 'next/image'
import Link from 'next/link'
import HeaderToHome from '@/components/ui/HeaderToHome'
import CompleteAddress from '@/components/pages/order/CompleteAddress'
import PayMoney from '@/components/pages/order/PayMoney'
import CompleteMoney from '@/components/pages/order/CompleteMoney'
import ContinueOrder from '@/components/pages/order/ContinueOrder'

export default function Complete() {
    return (
        <>
            <HeaderToHome title="주문완료" />
            <div>
                <h1 className="text-center text-xl font-bold h-20 flex justify-center items-center">
                    주문이 완료되었습니다.
                </h1>
            </div>
            <hr className="h-[10px] bg-[#f5f5f5] border-[#f5f5f5]" />
            <CompleteAddress />
            <hr className="h-[10px] bg-[#f5f5f5] border-[#f5f5f5]" />
            <PayMoney />
            <hr className="border-[#f5f5f5] h-[10px]" />
            <div className="m-3">
                <div className="flex mb-10 justify-between font-bold text-[20px]">
                    <span>카카오페이</span>
                    <CompleteMoney />
                </div>

                <div className="text-[13px] tracking-[-0.3px] font-normal">
                    <p>※ (주)에스에스지닷컴으로 발급되는 가상계좌로, 상품구매목적 이외의 입금은 불가합니다.</p>
                    <p>※ 입금 기한일까지 미 입금 시 자동 주문취소</p>
                </div>
            </div>
            <hr className="h-[10px] bg-[#f5f5f5] border-[#f5f5f5]" />
            <div className="m-3 ">
                <span className="font-bold">더 많은 혜택을 누리시려면</span>
                <Image
                    alt="유니버스 클럽 가입하기"
                    src={
                        'https://simg.ssgcdn.com/trans.ssg?src=/ui/m_ssg/img/order/mnodr_universe_type_banner01.png&w=750'
                    }
                    width={500}
                    height={500}
                    className="mx-auto mt-[12px]"
                />
            </div>

            <ContinueOrder />
        </>
    )
}
