'use client'
import Link from 'next/link'
import AddressList from './AddressList'
import Buttons from '@/components/ui/buttons'
import { changeMainDelivery } from '@/actions/delivery'
import { useState } from 'react'

interface AddressModalProps {
    modalOpen: boolean
    setModalOpen: (value: boolean) => void
}
function AddressModal({ modalOpen, setModalOpen }: AddressModalProps) {
    const handleMain = async (deliveryId: string) => {
        await changeMainDelivery(parseInt(deliveryId))
    }
    const [deliveryId, setDeliveryId] = useState('')
    return modalOpen ? (
        <div className="bg-black/60 absolute inset-0 z-50">
            <div className="fixed inset-x-0 top-7 bottom-0 flex flex-col border rounded-t-lg bg-white">
                {/* 모달 헤더 */}
                <div
                    className="h-14 flex justify-center items-center"
                    style={{ borderBottom: '1px solid', borderBottomColor: '#d1d1d1' }}
                >
                    <h3 className="font-bold right-4">배송지 변경</h3>
                    <button
                        className="absolute right-4"
                        onClick={() => {
                            setModalOpen(false)
                        }}
                    >
                        <img
                            width="24"
                            height="24"
                            src="https://img.icons8.com/fluency-systems-regular/48/x.png"
                            alt="x"
                        />
                    </button>
                </div>

                <AddressList setDeliveryId={setDeliveryId} />

                <div className="px-4">
                    <Link href={'/address'}>
                        <button className="w-full border h-14 mt-5" type="button">
                            <span className="text-sm font-light">+ 주소 추가하기</span>
                        </button>
                    </Link>
                </div>
                <div className="flex fixed bottom-0 left-0 right-0">
                    <div className="flex-grow">
                        <Buttons
                            title="기본 배송지 변경"
                            href="/cart"
                            click={() => {
                                setModalOpen(false)
                                handleMain(deliveryId)
                            }}
                        />
                    </div>
                </div>
            </div>
        </div>
    ) : null
}
export default AddressModal
