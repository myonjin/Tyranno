import OrderItems from '@/components/pages/mypage/OrderItems'
import Image from 'next/image'
export default function orderlist() {
    return (
        <main>
            <div className="flex items-center justify-center p-4" style={{ backgroundColor: '#f5f5f5' }}>
                <Image
                    width={25}
                    height={25}
                    src={'https://img.icons8.com/isometric-line/50/delivery.png'}
                    alt="delivery"
                />
                <span className="font-bold"> 주문배송</span>
            </div>
            <OrderItems />
        </main>
    )
}
