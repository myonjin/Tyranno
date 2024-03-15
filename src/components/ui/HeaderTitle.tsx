import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faArrowLeft } from '@fortawesome/free-solid-svg-icons'
import '@/components/ui/HeaderTitle.css'
import BackIcon from '@/images/back'

export default function HeaderTitle({ title }: { title: string }) {
    return (
        <div className="flex items-center h-12  border-b  border-gray-400">
            <span className="ml-4 ">
                <BackIcon />
            </span>
            <p className="flex-grow font-bold text-lg text-center">{title}</p>
        </div>
    )
}
