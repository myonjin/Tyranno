import ChangeAddressForm from '@/components/pages/mypage/ChangeAddressForm'

function Address({ params }: { params: { addressid: string } }) {
    return <ChangeAddressForm params={params.addressid} />
}
export default Address
