package zhu.app.model;

/**
 * 客服
 */
public class CustomerService {
	private int id;
	private int serviceId;
	private String serviceName;
	private String serviceQq;
	private String servicePhone;
	private String serviceNick;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceQq() {
		return serviceQq;
	}

	public void setServiceQq(String serviceQq) {
		this.serviceQq = serviceQq;
	}

	public String getServicePhone() {
		return servicePhone;
	}

	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}

	public String getServiceNick() {
		return serviceNick;
	}

	public void setServiceNick(String serviceNick) {
		this.serviceNick = serviceNick;
	}
}
